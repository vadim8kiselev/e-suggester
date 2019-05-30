package com.vk.api.sdk.queries.users;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.Utils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.enums.UsersNameCase;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.VKUserCounters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VKUsersGetQuery extends AbstractQueryBuilder<VKUsersGetQuery, List<VKUserCounters>> {

    public VKUsersGetQuery(VkApiClient client, UserActor actor) {
        super(client, "users.get", Utils.buildParametrizedType(List.class, VKUserCounters.class));
        this.accessToken(actor.getAccessToken());
    }

    public VKUsersGetQuery(VkApiClient client, GroupActor actor) {
        super(client, "users.get", Utils.buildParametrizedType(List.class, VKUserCounters.class));
        this.accessToken(actor.getAccessToken());
    }

    public VKUsersGetQuery(VkApiClient client, ServiceActor actor) {
        super(client, "users.get", Utils.buildParametrizedType(List.class, VKUserCounters.class));
        this.accessToken(actor.getAccessToken());
        this.clientSecret(actor.getClientSecret());
    }

    public VKUsersGetQuery userIds(String... value) {
        return this.unsafeParam("user_ids", value);
    }

    public VKUsersGetQuery userIds(List<String> value) {
        return this.unsafeParam("user_ids", value);
    }

    public VKUsersGetQuery fields(Fields... value) {
        return this.unsafeParam("fields", value);
    }

    protected VKUsersGetQuery getThis() {
        return this;
    }

    protected List<String> essentialKeys() {
        return Collections.singletonList("access_token");
    }
}
