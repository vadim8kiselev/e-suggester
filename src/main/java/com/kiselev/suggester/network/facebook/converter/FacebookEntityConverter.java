package com.kiselev.suggester.network.facebook.converter;

import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.network.vk.converter.EntityConverter;

import java.util.List;

public class FacebookEntityConverter<ExternalProfile, ExternalGroup, ExternalProduct> implements EntityConverter<ExternalProfile, ExternalGroup, ExternalProduct> {

    @Override
    public Profile convertProfile(ExternalProfile externalEntity) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Profile> convertProfiles(List<ExternalProfile> externalEntities) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public Group convertGroup(ExternalGroup externalGroup) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Group> convertGroups(List<ExternalGroup> externalGroups) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public Product convertProduct(ExternalProduct externalProduct) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }

    @Override
    public List<Product> convertProducts(List<ExternalProduct> externalProducts) {
        throw new UnsupportedOperationException("Facebook API is not implemented yet.");
    }
}
