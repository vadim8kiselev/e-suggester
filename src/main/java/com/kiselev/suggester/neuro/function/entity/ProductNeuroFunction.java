package com.kiselev.suggester.neuro.function.entity;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.neuro.function.NeuroFunction;
import org.springframework.stereotype.Component;

@Component
public class ProductNeuroFunction implements NeuroFunction<Product> {

    @Override
    public String process(Product product) {
        return null;
    }
}
