package com.vk.api.sdk.client;

import com.vk.api.sdk.actions.VKUsers;

public class HackVKApiClient {

    public VKUsers users(VkApiClient vk) {
        return new VKUsers(vk);
    }
}
