package com.kiselev.suggester.network.vk.implementation;

import com.google.gson.Gson;
import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.SocialNetworkAPI;
import com.kiselev.suggester.network.vk.annotation.Doc;
import com.kiselev.suggester.network.vk.configuration.VKAPIConfiguration;
import com.kiselev.suggester.network.vk.implementation.internal.VKAPIInternal;
import com.vk.api.sdk.client.HackVKApiClient;
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
        HackVKApiClient vkHack = new HackVKApiClient();
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance(), new Gson(), Integer.MAX_VALUE);
        UserActor user;

        final boolean TOKEN_UPDATED = true;

        try {
            if (TOKEN_UPDATED) {
                Integer userId = configuration.getUserId();
                String accessToken = configuration.getToken();
                user = new UserActor(userId, accessToken);
            } else {
                UserAuthResponse authResponse = vk.oAuth()
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

            api.auth(vkHack, vk, user);
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
    @Doc(method = "groups.get", maxCount = "1000")
    public List<Group> getGroupsByUserId(String userId) {
        return api.getGroupsByUserId(userId);
    }

    @Override
    @Doc(method = "market.get", maxCount = "200")
    public List<Product> getMarketProductsByGroupId(Integer groupId) {
        return api.getMarketProductsByGroupId(groupId);
    }
}
