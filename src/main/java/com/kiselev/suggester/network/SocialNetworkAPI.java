package com.kiselev.suggester.network;

import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface SocialNetworkAPI {

    Profile getProfileByProfileId(String profileId);

    List<Profile> getProfilesByProfilesIds(List<String> profilesIds);

    List<Group> getGroupsByUserId(String userId);

    List<Product> getMarketProductsByGroupId(Integer groupId);
}
