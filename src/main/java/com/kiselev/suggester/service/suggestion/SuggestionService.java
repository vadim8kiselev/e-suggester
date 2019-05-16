package com.kiselev.suggester.service.suggestion;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.suggestion.Suggester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionService {

    @Autowired
    private Suggester suggester;

    public List<Product> suggest(Profile profile) {
        return suggester.suggest(profile);
    }
}
