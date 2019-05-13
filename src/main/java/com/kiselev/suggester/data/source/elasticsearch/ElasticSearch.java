package com.kiselev.suggester.data.source.elasticsearch;

import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface ElasticSearch {

    void publishProfiles(List<Profile> profiles);
}
