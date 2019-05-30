package com.vk.api.sdk.actions;

import com.vk.api.sdk.client.AbstractAction;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.enums.UsersType;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.queries.users.*;

public class VKUsers extends AbstractAction {

    public VKUsers(VkApiClient client) {
        super(client);
    }

    public VKUsersGetQuery get(UserActor actor) {
        return new VKUsersGetQuery(this.getClient(), actor);
    }

    public VKUsersGetQuery get(GroupActor actor) {
        return new VKUsersGetQuery(this.getClient(), actor);
    }

    public VKUsersGetQuery get(ServiceActor actor) {
        return new VKUsersGetQuery(this.getClient(), actor);
    }
}
