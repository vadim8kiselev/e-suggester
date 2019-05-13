package com.kiselev.suggester.data.model.converter;

import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface ProfileConverter<Entity> extends Converter<Entity, Profile> {

    Profile convert(Entity entity);

    List<Profile> convert(List<Entity> entities);
}
