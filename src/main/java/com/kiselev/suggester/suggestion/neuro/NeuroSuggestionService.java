package com.kiselev.suggester.suggestion.neuro;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.Suggester;
import com.kiselev.suggester.suggestion.neuro.implementation.NeuroSuggester;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NeuroSuggestionService implements Suggester {

    @Autowired
    private NeuroSuggester neuroSuggester;

    @Override
    public List<Product> suggest(Profile profile) {
        return neuroSuggester.suggest(profile);
    }
}
