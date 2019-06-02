package com.kiselev.suggester.network.vk.implementation.internal;

import com.kiselev.suggester.network.SocialNetworkAPI;
import com.vk.api.sdk.client.HackVKApiClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

public interface VKAPIInternal extends SocialNetworkAPI {

    void auth(HackVKApiClient vkHack, VkApiClient vk, UserActor user);
}
