package com.kiselev.suggester.network.facebook.implementation;

import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
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
    public List<Group> getGroupsByUserId(String userId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Product> getMarketProductsByGroupId(Integer groupId) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
