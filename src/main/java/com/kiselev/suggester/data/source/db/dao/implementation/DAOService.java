package com.kiselev.suggester.data.source.db.dao.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.data.source.db.repository.ProductRepository;
import com.kiselev.suggester.data.source.db.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class DAOService implements DAO {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    private boolean fallback = true;

    @Override
    public void profiles(Iterable<Profile> profiles) {
        try {
            if (this.fallback) {
                profileRepository.save(filterDeactivatedProfiles(profiles));
            }
        } catch (Exception exception) {
            this.fallback = false;
        }
    }

    @Override
    public Iterable<Profile> profiles() {
        try {
            if (this.fallback) {
                return profileRepository.findAll();
            }
        } catch (Exception exception) {
            this.fallback = false;
        }

        return Lists.newArrayList();
    }

    @Override
    public void products(Iterable<Product> products) {
        try {
            if (this.fallback) {
                productRepository.save(products);
            }
        } catch (Exception exception) {
            this.fallback = false;
        }
    }

    @Override
    public Iterable<Product> products() {
        try {
            if (this.fallback) {
                return productRepository.findAll();
            }
        } catch (Exception exception) {
            this.fallback = false;
        }

        return Lists.newArrayList();
    }

    private Iterable<Profile> filterDeactivatedProfiles(Iterable<Profile> profiles) {
        return Lists.newArrayList(profiles).stream()
                .filter(profile -> profile.getDeactivated().isEmpty())
                .collect(Collectors.toList());
    }
}
