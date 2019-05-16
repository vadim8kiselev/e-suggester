package com.kiselev.suggester.suggestion.neuro.function.entity;

import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.neuro.function.NeuroFunction;
import org.springframework.stereotype.Component;

public class ProfileNeuroFunction implements NeuroFunction<Profile> {

    @Override
    public String process(Profile profile) {
        return "";
    }
}
