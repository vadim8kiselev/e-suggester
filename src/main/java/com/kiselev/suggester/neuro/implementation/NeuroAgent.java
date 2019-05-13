package com.kiselev.suggester.neuro.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.neuro.NeuroSuggester;
import com.kiselev.suggester.neuro.function.NeuroFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeuroAgent implements NeuroSuggester {

    @Autowired
    private NeuroFunction<Profile> profileFunction;

    @Override
    public List<Product> suggest(Profile profile) {
        String function = profileFunction.process(profile);

        return Lists.newArrayList(
                new Product("product_by_profile_with_name_" + profile.getFirstName() + "_#1"),
                new Product("product_by_profile_with_name_" + profile.getFirstName() + "_#2"),
                new Product("product_by_profile_with_name_" + profile.getFirstName() + "_#3")
        );
    }
}
