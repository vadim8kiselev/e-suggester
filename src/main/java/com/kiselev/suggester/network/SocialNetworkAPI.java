package com.kiselev.suggester.network;

import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface SocialNetworkAPI {

    Profile getProfileByProfileId(String profileId);

    List<Profile> getProfilesByProfilesIds(List<String> profilesIds);

    List<Profile> getFriendsByProfileId(String profileId);

    List<Profile> getFollowersByProfileId(String profileId);

    List<Profile> getSubscriptionsByProfileId(String profileId);
}
