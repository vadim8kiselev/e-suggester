package com.kiselev.suggester.network.vk.converter.implementation;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.vk.converter.EntityConverter;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.groups.ContactsItem;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.GroupIsClosed;
import com.vk.api.sdk.objects.groups.LinksItem;
import com.vk.api.sdk.objects.market.MarketItemAvailability;
import com.vk.api.sdk.objects.market.MarketItemFull;
import com.vk.api.sdk.objects.users.VKUserCounters;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VKEntityConverter implements EntityConverter<VKUserCounters, GroupFull, MarketItemFull> {

    @Override
    public Profile convertProfile(VKUserCounters externalUser) {
        return Profile.builder()
                .id(externalUser.getId().toString())
                .firstName(externalUser.getFirstName())
                .lastName(externalUser.getLastName())
                .sex(nonNull(externalUser.getSex()))
                .birthday(dateToString(nonNull(externalUser.getBdate())))
                .online(String.valueOf(externalUser.isOnline() || externalUser.isOnlineMobile()))
                .photoLink(nonNull(externalUser.getPhotoMaxOrig()))

                .city(externalUser.getCity() != null ? externalUser.getCity().getTitle() : "")
                .homeTown(nonNull(externalUser.getHomeTown()))
                .country(externalUser.getCountry() != null ? externalUser.getCountry().getTitle() : "")

                .mobilePhone(nonNull(externalUser.getMobilePhone()))
                .homePhone(nonNull(externalUser.getHomePhone()))

                .status(nonNull(externalUser.getStatus()))

                .relation(VKPropertyMapper.getRelation(nonNull(externalUser.getRelation())))

                .interests(simplify(clean(lower(nonNull(externalUser.getInterests()))))) // DONE HERE
                .music(simplify(clean(lower(nonNull(externalUser.getMusic())))))
                .activities(simplify(clean(lower(nonNull(externalUser.getActivities())))))
                .movies(simplify(clean(lower(nonNull(externalUser.getMovies())))))
                .tv(simplify(clean(lower(nonNull(externalUser.getTv())))))
                .books(simplify(clean(lower(nonNull(externalUser.getBooks())))))
                .games(simplify(clean(lower(nonNull(externalUser.getGames())))))
                .about(simplify(clean(lower(nonNull(externalUser.getAbout())))))
                .quotes(simplify(clean(lower(nonNull(externalUser.getQuotes())))))

                .political(externalUser.getPersonal() != null ? VKPropertyMapper.getPoliticalViews(nonNull(externalUser.getPersonal().getPolitical())) : "")
                .languages(externalUser.getPersonal() != null ? listToString(nonNull(externalUser.getPersonal().getLangs())) : "")
                .religion(externalUser.getPersonal() != null ? nonNull(externalUser.getPersonal().getReligion()) : "")
                .inspiredBy(externalUser.getPersonal() != null ? nonNull(externalUser.getPersonal().getInspiredBy()) : "")
                .peopleMain(externalUser.getPersonal() != null ? VKPropertyMapper.getImportantInOthers(nonNull(externalUser.getPersonal().getPeopleMain())) : "")
                .lifeMain(externalUser.getPersonal() != null ? VKPropertyMapper.getPersonalPriority(nonNull(externalUser.getPersonal().getLifeMain())) : "")
                .smoking(externalUser.getPersonal() != null ? VKPropertyMapper.getSmokingAndAlcohol(nonNull(externalUser.getPersonal().getSmoking())) : "")
                .alcohol(externalUser.getPersonal() != null ? VKPropertyMapper.getSmokingAndAlcohol(nonNull(externalUser.getPersonal().getAlcohol())) : "")

                .closed(Boolean.valueOf(externalUser.isClosed()))
                .deactivated(externalUser.getDeactivated() != null)

                .products(Sets.newHashSet())
                .build();
    }

    @Override
    public List<Profile> convertProfiles(List<VKUserCounters> externalUsers) {
        return externalUsers.stream()
                .map(this::convertProfile)
                .collect(Collectors.toList());
    }

    @Override
    public Group convertGroup(GroupFull externalGroup) {
        return Group.builder()
                .id(externalGroup.getId().toString())
                .name(externalGroup.getName())
                .screenName(nonNull(externalGroup.getScreenName()))
                .status(nonNull(externalGroup.getStatus()))
                .description(nonNull(externalGroup.getDescription()))

                .type(nonNull(externalGroup.getActivity()))
                .verified(String.valueOf(externalGroup.isVerified()))
                .closed(externalGroup.getIsClosed() != GroupIsClosed.OPEN)
                .ageLimits(nonNull(externalGroup.getAgeLimits()))
                .subscribersCount(nonNull(externalGroup.getMembersCount()))

                .city(nonNull(externalGroup.getCity()))
                .country(nonNull(externalGroup.getCountry()))

                .photoLink(nonNull(externalGroup.getPhoto200()))

                .links(listToString(nonNull(externalGroup.getLinks())))
                .contacts(listToString(nonNull(externalGroup.getContacts())))
                .site(nonNull(externalGroup.getSite()))

                .marketEnabled(externalGroup.getMarket() != null
                        && externalGroup.getMarket().getEnabled() == BoolInt.YES)

                .deactivated(nonNull(externalGroup.getDeactivated()))
                .build();
    }

    @Override
    public List<Group> convertGroups(List<GroupFull> externalGroups) {
        return externalGroups.stream()
                .map(this::convertGroup)
                .collect(Collectors.toList());
    }

    @Override
    public Product convertProduct(MarketItemFull marketItem) {
        return Product.builder()
                .id(marketItem.getOwnerId() + "#" + marketItem.getId())
                .name(marketItem.getTitle())
                .type(marketItem.getCategory().getName())
                .price(toNumber(marketItem.getPrice().getAmount()))
                .availability(marketItem.getAvailability() == MarketItemAvailability.AVAILABLE)
                .views(marketItem.getViewsCount())
                .popularity(marketItem.getLikes().getCount())
                .photo(nonNull(marketItem.getThumbPhoto()))
                .url(nonNull(marketItem.getUrl()))
                .build();
    }

    private Integer toNumber(String string) {
        return Long.valueOf(String.valueOf(Long.valueOf(string) / 100)).intValue();
    }

    @Override
    public List<Product> convertProducts(List<MarketItemFull> marketItems) {
        return marketItems.stream()
                .map(this::convertProduct)
                .collect(Collectors.toList());
    }

    private String nonNull(String data) {
        return data != null ? data : "";
    }

    private <ExternalPojo> List<ExternalPojo> nonNull(List<ExternalPojo> data) {
        return data != null ? data : Lists.newArrayList();
    }

    private String nonNull(Object object) {
        return object != null ? object.toString() : "";
    }

    private String nonNull(Enum enumObject) {
        return enumObject != null ? enumObject.name() : "";
    }

    private String lower(String data) {
        return data != null ? data.toLowerCase() : "";
    }

    private String simplify(String data) {
        return data != null ? data.replaceAll("\\s{2,}", " ") : "";
    }

    private String clean(String data) {
        return data != null ? data.replaceAll("[^a-zA-Zа-яА-Я,.;/\\\\]", " ") : "";
    }

    private <ExternalPojo> String listToString(List<ExternalPojo> data) {
        return String.join("; ", data.stream()
                .map(this::entityToString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private <ExternalPojo> String entityToString(ExternalPojo entity) {
        if (entity instanceof LinksItem) {
            return linkToString((LinksItem) entity);
        } else if (entity instanceof ContactsItem) {
            return contactToString((ContactsItem) entity);
        } else if (entity != null) {
            return entity.toString();
        } else {
            return "";
        }
    }

    private String dateToString(String date) {
        return Pattern.matches("\\d+\\.\\d+\\.\\d+", date) ? date : "";
    }

    private String linkToString(LinksItem link) {
        return link.getName() != null && link.getUrl() != null ? link.getName() + " - " + link.getUrl() : null;
    }

    private String contactToString(ContactsItem contact) {
        Integer userId = contact.getUserId();
        String email = contact.getEmail() != null ? " - " + contact.getEmail() : "";
        String phone = contact.getPhone() != null ? " - " + contact.getPhone() : "";
        return userId != null ? userId + email + phone : null;
    }
}
