package com.kiselev.suggester.data.source.db.dao;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;

import java.util.List;
import java.util.Set;

public interface DAO {

    /* Profiles*/

    void profile(Profile profile);

    void profiles(List<Profile> profiles);

    Profile profile(String id);

    List<Profile> profiles();

    /* Products */

    void product(Product product);

    void products(Set<Product> products);

    Product product(String id);

    List<Product> products();

    List<Product> products(String type);
}
