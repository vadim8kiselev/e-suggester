package com.kiselev.suggester.data.source.db.dao;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;

public interface DAO {

    void profile(Profile profile);

    void profiles(List<Profile> profiles);

    Profile profile(String id);

    List<Profile> profiles();

    void product(Product product);

    void products(List<Product> products);

    Product product(String id);

    List<Product> products();
}
