package com.kiselev.suggester.network.vk.implementation.internal;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.vk.converter.EntityConverter;
import com.kiselev.suggester.network.vk.exception.ExceptionHandler;
import com.kiselev.suggester.network.vk.utils.VKUtils;
import com.vk.api.sdk.client.HackVKApiClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.market.MarketItemFull;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.VKUserCounters;
import com.vk.api.sdk.queries.groups.GroupsGetQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

import static com.kiselev.suggester.network.vk.constants.VKConstants.MaxCount;

public class VKAPIInternalProvider implements VKAPIInternal {

    @Autowired
    private EntityConverter<VKUserCounters, GroupFull, MarketItemFull> converter;

    private HackVKApiClient vkHack;

    private VkApiClient vk;

    private UserActor user;

    @Override
    public void auth(HackVKApiClient vkHack, VkApiClient vk, UserActor user) {
        this.vkHack = vkHack;
        this.vk = vk;
        this.user = user;
    }

    @Override
    @Cacheable("profiles")
    public Profile getProfileByProfileId(String profileId) {
        try {
            VKUtils.timeout();
            return vkHack.users(vk)
                    .get(user)
                    .fields(Fields.values())
                    .userIds(profileId)
                    .execute().stream()
                    .map(converter::convertProfile)
                    .findFirst().orElse(null);
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return null;
    }

    @Override
    @Cacheable("profiles")
    public List<Profile> getProfilesByProfilesIds(List<String> profilesIds) {
        List<List<String>> lists = Lists.partition(profilesIds, MaxCount.USERS_GET);
        return lists.stream()
                .map(list -> {
                    try {
                        VKUtils.timeout();
                        return vkHack.users(vk)
                                .get(user)
                                .fields(Fields.values())
                                .userIds(list)
                                .execute().stream()
                                .map(converter::convertProfile)
                                .collect(Collectors.toList());
                    } catch (ApiException | ClientException exception) {
                        ExceptionHandler.rethrowException(exception);
                    }
                    return Lists.<Profile>newArrayList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("groups")
    public List<Group> getGroupsByUserId(String userId) {
        try {
            GroupsGetQuery query = vk.groups().get(user)
                    .userId(Integer.parseInt(userId))
                    .fields(com.vk.api.sdk.objects.groups.Fields.values())
                    .count(MaxCount.GROUPS_GET);

            int offset = 0;
            GetResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<Integer> groupsIds = response.getItems();
            while (MaxCount.GROUPS_GET.equals(response.getItems().size())) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                groupsIds.addAll(response.getItems());
                offset += MaxCount.GROUPS_GET;
            }

            return getGroupsByGroupsIds(VKUtils.toStringList(groupsIds));
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return Lists.newArrayList();
    }

    private List<Group> getGroupsByGroupsIds(List<String> groupsIds) {
        return Lists.partition(groupsIds, MaxCount.GROUPS_GET_BY_ID).stream()
                .map(groups -> {
                    try {
                        VKUtils.timeout();
                        List<GroupFull> externalGroups = vk.groups().getById(user)
                                .fields(com.vk.api.sdk.objects.groups.Fields.values())
                                .groupIds(groups)
                                .execute();
                        return converter.convertGroups(externalGroups);
                    } catch (ApiException | ClientException exception) {
                        ExceptionHandler.rethrowException(exception);
                    }
                    return Lists.<Group>newArrayList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("products")
    public List<Product> getMarketProductsByGroupId(Integer groupId) {
        try {
            VKUtils.timeout();

            return vk.market()
                    .getExtended(user, groupId)
                    .execute().getItems().stream()
                    .map(converter::convertProduct)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return Lists.newArrayList();
    }
}
