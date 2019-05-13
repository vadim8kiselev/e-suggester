package com.kiselev.suggester.network.facebook.implementation;

import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.SocialNetworkAPI;

import java.util.List;

public class FacebookAPI implements SocialNetworkAPI {

    @Override
    public Profile getProfileByProfileId(String profileId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> getProfilesByProfilesIds(List<String> profilesIds) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> getFriendsByProfileId(String profileId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> getFollowersByProfileId(String profileId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> getSubscriptionsByProfileId(String profileId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
