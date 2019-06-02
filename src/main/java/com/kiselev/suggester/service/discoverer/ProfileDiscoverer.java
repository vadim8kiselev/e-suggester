package com.kiselev.suggester.service.discoverer;

import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.network.SocialNetworkAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProfileDiscoverer {

    private static final String SLASH = "/";

    private static final String ID = "id";

    @Value("${discovery.group.limit:100}")
    private Integer groupLimit;

    @Value("${discovery.cache.enabled:false}")
    private Boolean cacheEnabled;

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    @Autowired
    private DAO dao;

    public Profile discover(String link) {
        String profileId = normalize(link);

        Profile cachedProfile = dao.profile(profileId);

        if (cachedProfile != null && cacheEnabled) {
            return cachedProfile;
        } else {
            Profile profile = socialNetworkAPI.getProfileByProfileId(profileId);

            if (!profile.getClosed() && !profile.getDeactivated()) {
                List<Group> groups = socialNetworkAPI.getGroupsByUserId(profile.getId());

                Set<Product> products = groups.stream()
                        .filter(group -> !group.getClosed() && group.getMarketEnabled())
                        .limit(groupLimit)
                        .map(Group::getOwnerId)
                        .map(socialNetworkAPI::getMarketProductsByGroupId)
                        .flatMap(List::stream)
                        .collect(Collectors.toSet());

                profile.setProducts(products);

                dao.profile(profile);

                return profile;
            }

            return null;
        }
    }

    private String normalize(String link) {
        if (link.contains(SLASH)) {
            link = link.substring(link.lastIndexOf(SLASH) + 1);
        }

        if (link.startsWith(ID) && isNumber(link.substring(ID.length()))) {
            link = link.replaceFirst(ID, "");
        }

        return link;
    }

    private boolean isNumber(String value) {
        return value.matches("^[1-9]\\d*$");
    }
}
