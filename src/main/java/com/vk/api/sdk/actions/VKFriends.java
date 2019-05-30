package com.vk.api.sdk.actions;

import com.vk.api.sdk.client.AbstractAction;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.queries.friends.*;

import java.util.List;

public class VKFriends extends AbstractAction {

    public VKFriends(VkApiClient client) {
        super(client);
    }

    public FriendsAddQuery add(UserActor actor) {
        return new FriendsAddQuery(this.getClient(), actor);
    }

    public FriendsAddListQuery addList(UserActor actor, String name) {
        return new FriendsAddListQuery(this.getClient(), actor, name);
    }

    public FriendsAreFriendsQuery areFriends(UserActor actor, Integer... userIds) {
        return new FriendsAreFriendsQuery(this.getClient(), actor, userIds);
    }

    public FriendsAreFriendsQuery areFriends(UserActor actor, List<Integer> userIds) {
        return new FriendsAreFriendsQuery(this.getClient(), actor, userIds);
    }

    public FriendsDeleteQuery delete(UserActor actor) {
        return new FriendsDeleteQuery(this.getClient(), actor);
    }

    public FriendsDeleteAllRequestsQuery deleteAllRequests(UserActor actor) {
        return new FriendsDeleteAllRequestsQuery(this.getClient(), actor);
    }

    public FriendsDeleteListQuery deleteList(UserActor actor, int listId) {
        return new FriendsDeleteListQuery(this.getClient(), actor, listId);
    }

    public FriendsEditQuery edit(UserActor actor, int userId) {
        return new FriendsEditQuery(this.getClient(), actor, userId);
    }

    public FriendsEditListQuery editList(UserActor actor, int listId) {
        return new FriendsEditListQuery(this.getClient(), actor, listId);
    }

    public VKFriendsGetQueryWithFields getWithFields(UserActor actor, Fields... fields) {
        return new VKFriendsGetQueryWithFields(this.getClient(), actor, fields);
    }

    public VKFriendsGetQueryWithFields getWithFields(ServiceActor actor, Fields... fields) {
        return new VKFriendsGetQueryWithFields(this.getClient(), actor, fields);
    }

    public FriendsGetQuery get(UserActor actor) {
        return new FriendsGetQuery(this.getClient(), actor);
    }

    public FriendsGetQuery get(ServiceActor actor) {
        return new FriendsGetQuery(this.getClient(), actor);
    }

    public FriendsGetAppUsersQuery getAppUsers(UserActor actor) {
        return new FriendsGetAppUsersQuery(this.getClient(), actor);
    }

    public FriendsGetByPhonesQuery getByPhones(UserActor actor) {
        return new FriendsGetByPhonesQuery(this.getClient(), actor);
    }

    public FriendsGetListsQuery getLists(UserActor actor) {
        return new FriendsGetListsQuery(this.getClient(), actor);
    }

    public FriendsGetMutualQuery getMutual(UserActor actor) {
        return new FriendsGetMutualQuery(this.getClient(), actor);
    }

    public FriendsGetMutualQueryWithTargetUids getMutualWithTargetUids(UserActor actor, Integer... targetUids) {
        return new FriendsGetMutualQueryWithTargetUids(this.getClient(), actor, targetUids);
    }

    public FriendsGetOnlineQueryWithOnlineMobile getOnlineWithOnlineMobile(UserActor actor, Boolean onlineMobile) {
        return new FriendsGetOnlineQueryWithOnlineMobile(this.getClient(), actor, onlineMobile);
    }

    public FriendsGetOnlineQuery getOnline(UserActor actor) {
        return new FriendsGetOnlineQuery(this.getClient(), actor);
    }

    public FriendsGetRecentQuery getRecent(UserActor actor) {
        return new FriendsGetRecentQuery(this.getClient(), actor);
    }

    public FriendsGetRequestsQueryWithExtended getRequestsExtended(UserActor actor) {
        return new FriendsGetRequestsQueryWithExtended(this.getClient(), actor);
    }

    public FriendsGetRequestsQueryWithNeedMutual getRequestsWithNeedMutual(UserActor actor, Boolean needMutual) {
        return new FriendsGetRequestsQueryWithNeedMutual(this.getClient(), actor, needMutual);
    }

    public FriendsGetRequestsQuery getRequests(UserActor actor) {
        return new FriendsGetRequestsQuery(this.getClient(), actor);
    }

    public FriendsGetSuggestionsQuery getSuggestions(UserActor actor) {
        return new FriendsGetSuggestionsQuery(this.getClient(), actor);
    }

    public FriendsSearchQuery search(UserActor actor, int userId) {
        return new FriendsSearchQuery(this.getClient(), actor, userId);
    }
}
