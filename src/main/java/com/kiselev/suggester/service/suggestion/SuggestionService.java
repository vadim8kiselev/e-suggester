package com.kiselev.suggester.service.suggestion;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.neuro.NeuroSuggester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionService {

    @Autowired
    private NeuroSuggester neuroSuggester;

    public List<Product> suggest(Profile profile) {
        return neuroSuggester.suggest(profile);
    }
}
