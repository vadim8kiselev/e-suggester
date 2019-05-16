package com.kiselev.suggester.data.model.converter;

import com.kiselev.suggester.data.model.entity.Product;

import java.util.List;

public interface ProductConverter<Entity> extends Converter<Entity, Product> {

    Product convert(Entity entity);

    List<Product> convert(List<Entity> entities);
}
