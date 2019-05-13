package com.kiselev.suggester.network.vk.converter;

import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface EntityConverter<ExternalUser> {

    Profile convertProfile(ExternalUser externalEntity);

    List<Profile> convertProfiles(List<ExternalUser> externalEntities);
}
