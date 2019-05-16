package com.kiselev.suggester.suggestion;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface Suggester {

    List<Product> suggest(Profile profile);
}
