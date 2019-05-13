package com.kiselev.suggester.neuro;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface NeuroSuggester {

    List<Product> suggest(Profile profile);
}
