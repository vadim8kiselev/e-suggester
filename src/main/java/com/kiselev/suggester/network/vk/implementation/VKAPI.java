package com.kiselev.suggester.network.vk.implementation;

import com.google.gson.Gson;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.SocialNetworkAPI;
import com.kiselev.suggester.network.vk.annotation.Doc;
import com.kiselev.suggester.network.vk.configuration.VKAPIConfiguration;
import com.kiselev.suggester.network.vk.implementation.internal.VKAPIInternal;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public class VKAPI implements SocialNetworkAPI {

    @Autowired
    private VKAPIInternal api;

    @Autowired
    private VKAPIConfiguration configuration;

    @PostConstruct
    public void auth() {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance(), new Gson(), Integer.MAX_VALUE);
        UserActor user;

        final boolean TOKEN_UPDATED = true;

        try {
            if (TOKEN_UPDATED) {
                Integer userId = configuration.getUserId();
                String accessToken = configuration.getToken();
                user = new UserActor(userId, accessToken);
            } else {
                UserAuthResponse authResponse = vk.oauth()
                        .userAuthorizationCodeFlow(configuration.getClientId(),
                                configuration.getClientSecret(),
                                configuration.getRedirectUri(),
                                configuration.getSecretCode())
                        .execute();

                Integer userId = authResponse.getUserId();
                String accessToken = authResponse.getAccessToken();
                user = new UserActor(userId, accessToken);

                System.out.println("\n\nAccess token: " + accessToken + "\n\n");
            }

            api.auth(vk, user);
        } catch (ApiException | ClientException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * User related methods
     */
    @Override
    @Doc(method = "users.get", maxCount = "1")
    public Profile getProfileByProfileId(String profileId) {
        return api.getProfileByProfileId(profileId);
    }

    @Override
    @Doc(method = "users.get", maxCount = "1000")
    public List<Profile> getProfilesByProfilesIds(List<String> profilesIds) {
        return api.getProfilesByProfilesIds(profilesIds);
    }

    @Override
    @Doc(method = "friends.get", maxCount = "5000")
    public List<Profile> getFriendsByProfileId(String profileId) {
        return api.getFriendsByProfileId(profileId);
    }

    @Override
    @Doc(method = "users.getFollowers", maxCount = "1000")
    public List<Profile> getFollowersByProfileId(String profileId) {
        return api.getFollowersByProfileId(profileId);
    }

    @Override
    @Doc(method = "users.getSubscriptions", maxCount = "200")
    public List<Profile> getSubscriptionsByProfileId(String profileId) {
        return api.getSubscriptionsByProfileId(profileId);
    }
}
