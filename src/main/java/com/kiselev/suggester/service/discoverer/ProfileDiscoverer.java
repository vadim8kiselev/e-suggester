package com.kiselev.suggester.service.discoverer;

import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.SocialNetworkAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileDiscoverer {

    @Autowired
    private SocialNetworkAPI socialNetworkAPI;

    public Profile discover(String link) {
        return socialNetworkAPI.getProfileByProfileId(link.substring(link.lastIndexOf("/") + 1));
    }
}
