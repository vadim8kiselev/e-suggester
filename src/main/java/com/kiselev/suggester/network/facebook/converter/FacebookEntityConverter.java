package com.kiselev.suggester.network.facebook.converter;

import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.vk.converter.EntityConverter;

import java.util.List;

public class FacebookEntityConverter<ExternalUser> implements EntityConverter<ExternalUser> {

    @Override
    public Profile convertProfile(ExternalUser externalEntity) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> convertProfiles(List<ExternalUser> externalEntities) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
