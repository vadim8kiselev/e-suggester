package com.kiselev.suggester.data.source.db.dao;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

public interface DAO {

    void profiles(Iterable<Profile> profiles);

    Iterable<Profile> profiles();

    void products(Iterable<Product> products);

    Iterable<Product> products();
}
