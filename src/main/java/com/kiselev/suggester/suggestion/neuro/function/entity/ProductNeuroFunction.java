package com.kiselev.suggester.suggestion.neuro.function.entity;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.suggestion.neuro.function.NeuroFunction;
import org.springframework.stereotype.Component;

public class ProductNeuroFunction implements NeuroFunction<Product> {

    @Override
    public String process(Product product) {
        return "";
    }
}
