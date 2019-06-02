package com.kiselev.suggester.suggestion.neuro.implementation.function.implementation;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.enums.Section;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;

public class ProductNeuroFunction implements NeuroFunction<Product> {

    @Override
    public String process(Product product) {
        return productToCategory(product);
    }

    private String productToCategory(Product product) {
        return String.valueOf(Section.byTitle(product.getSection()));
    }
}
