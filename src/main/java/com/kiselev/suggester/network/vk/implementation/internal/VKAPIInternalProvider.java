package com.kiselev.suggester.network.vk.implementation.internal;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.vk.converter.EntityConverter;
import com.kiselev.suggester.network.vk.exception.ExceptionHandler;
import com.kiselev.suggester.network.vk.utils.VKUtils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetFieldsResponse;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.users.responses.GetFollowersFieldsResponse;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQueryWithFields;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.users.UsersGetFollowersQueryWithFields;
import com.vk.api.sdk.queries.users.UsersGetSubscriptionsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

import static com.kiselev.suggester.network.vk.constants.VKConstants.MaxCount;

public class VKAPIInternalProvider implements VKAPIInternal {

    @Autowired
    private EntityConverter<UserFull> converter;

    private VkApiClient vk;

    private UserActor user;

    @Override
    public void auth(VkApiClient vk, UserActor user) {
        this.vk = vk;
        this.user = user;
    }

    @Override
    @Cacheable("profiles")
    public Profile getProfileByProfileId(String profileId) {
        try {
            VKUtils.timeout();
            return vk.users()
                    .get(user)
                    .fields(UserField.values())
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
                        return vk.users()
                                .get(user)
                                .fields(UserField.values())
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
    @Cacheable("friends")
    public List<Profile> getFriendsByProfileId(String profileId) {
        try {
            List<UserFull> friends = Lists.newArrayList();

            FriendsGetQueryWithFields query = vk.friends()
                    .get(user, UserField.values())
                    .userId(Integer.parseInt(profileId))
                    .count(MaxCount.FRIENDS_GET);

            GetFieldsResponse execute = query.execute();
            friends.addAll(execute.getItems());

            if (MaxCount.FRIENDS_GET.equals(execute.getItems().size())) {
                friends.addAll(query
                        .offset(MaxCount.FRIENDS_GET)
                        .execute().getItems());
            }

            return converter.convertProfiles(friends);
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("followers")
    public List<Profile> getFollowersByProfileId(String profileId) {
        try {
            UsersGetFollowersQueryWithFields query = vk.users()
                    .getFollowers(user, UserField.values())
                    .userId(Integer.parseInt(profileId))
                    .count(MaxCount.USERS_GET_FOLLOWERS);

            int offset = 0;
            GetFollowersFieldsResponse response = query.offset(offset).execute();

            List<UserFull> followers = response.getItems();
            while (MaxCount.USERS_GET_FOLLOWERS.equals(response.getItems().size())) {
                response = query.offset(offset).execute();
                followers.addAll(response.getItems());
                offset += MaxCount.USERS_GET_FOLLOWERS;
            }

            return followers.stream()
                    .map(converter::convertProfile)
                    .collect(Collectors.toList());
        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return Lists.newArrayList();
    }

    @Override
    @Cacheable("subscriptions")
    public List<Profile> getSubscriptionsByProfileId(String profileId) {
        try {
            UsersGetSubscriptionsQuery query = vk.users()
                    .getSubscriptions(user)
                    .userId(Integer.parseInt(profileId))
                    .count(MaxCount.USERS_GET_SUBSCRIPTIONS);

            int offset = 0;
            GetSubscriptionsResponse response = query.offset(offset).execute();
            VKUtils.timeout();

            List<Integer> subscriptionsIds = response.getUsers().getItems();
            while (MaxCount.USERS_GET_SUBSCRIPTIONS.equals(response.getUsers().getItems().size())) {
                response = query.offset(offset).execute();
                VKUtils.timeout();
                subscriptionsIds.addAll(response.getUsers().getItems());
                offset += MaxCount.USERS_GET_SUBSCRIPTIONS;
            }

            return getProfilesByProfilesIds(VKUtils.toStringList(subscriptionsIds));

        } catch (ApiException | ClientException exception) {
            ExceptionHandler.rethrowException(exception);
        }
        return Lists.newArrayList();
    }
}
