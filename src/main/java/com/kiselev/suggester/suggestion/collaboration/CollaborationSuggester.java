package com.kiselev.suggester.suggestion.collaboration;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.Suggester;

import java.util.List;

public class CollaborationSuggester implements Suggester {

    @Override
    public List<Product> suggest(Profile profile) {
        return Lists.newArrayList();
    }
}
