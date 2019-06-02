package com.kiselev.suggester.network.vk.converter;

import com.kiselev.suggester.data.model.entity.Group;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface EntityConverter<ExternalUser, ExternalGroup, ExternalProduct> {

    Profile convertProfile(ExternalUser externalEntity);

    List<Profile> convertProfiles(List<ExternalUser> externalEntities);

    Group convertGroup(ExternalGroup externalGroup);

    List<Group> convertGroups(List<ExternalGroup> externalGroups);

    Product convertProduct(ExternalProduct externalProduct);

    List<Product> convertProducts(List<ExternalProduct> externalProducts);
}
