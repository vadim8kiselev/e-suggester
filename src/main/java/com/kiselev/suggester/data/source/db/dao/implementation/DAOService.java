package com.kiselev.suggester.data.source.db.dao.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.data.source.db.repository.ProductRepository;
import com.kiselev.suggester.data.source.db.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class DAOService implements DAO {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void profile(Profile profile) {
        if (!profileRepository.exists(profile.getId())) {
            profileRepository.save(profile);
        }
    }

    @Override
    public void profiles(List<Profile> profiles) {
        for (Profile profile : profiles) {
            profile(profile);
        }
    }

    @Override
    public Profile profile(String id) {
        return profileRepository.findOne(id);
    }

    @Override
    public List<Profile> profiles() {
        return Lists.newArrayList(profileRepository.findAll());
    }

    @Override
    public void product(Product product) {
        if (!productRepository.exists(product.getId())) {
            productRepository.save(product);
        }
    }

    @Override
    public void products(Set<Product> products) {
        for (Product product : products) {
            product(product);
        }
    }

    @Override
    public Product product(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> products() {
        return Lists.newArrayList(productRepository.findAll());
    }

    @Override
    public List<Product> products(String type) {
        return productRepository.findByType(type);
    }
}
