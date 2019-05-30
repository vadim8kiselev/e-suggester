//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.vk.api.sdk.queries.friends;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.enums.FriendsNameCase;
import com.vk.api.sdk.objects.enums.FriendsOrder;
import com.vk.api.sdk.objects.friends.responses.VKGetFieldsResponse;
import com.vk.api.sdk.objects.users.Fields;

import java.util.Arrays;
import java.util.List;

public class VKFriendsGetQueryWithFields extends AbstractQueryBuilder<VKFriendsGetQueryWithFields, VKGetFieldsResponse> {

    public VKFriendsGetQueryWithFields(VkApiClient client, UserActor actor, Fields... fields) {
        super(client, "friends.get", VKGetFieldsResponse.class);
        this.accessToken(actor.getAccessToken());
        this.fields(fields);
    }

    public VKFriendsGetQueryWithFields(VkApiClient client, UserActor actor, List<Fields> fields) {
        super(client, "friends.get", VKGetFieldsResponse.class);
        this.accessToken(actor.getAccessToken());
        this.fields(fields);
    }

    public VKFriendsGetQueryWithFields(VkApiClient client, ServiceActor actor, Fields... fields) {
        super(client, "friends.get", VKGetFieldsResponse.class);
        this.accessToken(actor.getAccessToken());
        this.clientSecret(actor.getClientSecret());
        this.fields(fields);
    }

    public VKFriendsGetQueryWithFields(VkApiClient client, ServiceActor actor, List<Fields> fields) {
        super(client, "friends.get", VKGetFieldsResponse.class);
        this.accessToken(actor.getAccessToken());
        this.clientSecret(actor.getClientSecret());
        this.fields(fields);
    }

    public VKFriendsGetQueryWithFields userId(Integer value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("user_id", value);
    }

    public VKFriendsGetQueryWithFields order(FriendsOrder value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("order", value);
    }

    public VKFriendsGetQueryWithFields listId(Integer value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("list_id", value);
    }

    public VKFriendsGetQueryWithFields count(Integer value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("count", value);
    }

    public VKFriendsGetQueryWithFields offset(Integer value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("offset", value);
    }

    public VKFriendsGetQueryWithFields nameCase(FriendsNameCase value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("name_case", value);
    }

    protected VKFriendsGetQueryWithFields fields(Fields... value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("fields", value);
    }

    protected VKFriendsGetQueryWithFields fields(List<Fields> value) {
        return (VKFriendsGetQueryWithFields) this.unsafeParam("fields", value);
    }

    protected VKFriendsGetQueryWithFields getThis() {
        return this;
    }

    protected List<String> essentialKeys() {
        return Arrays.asList("access_token");
    }
}
