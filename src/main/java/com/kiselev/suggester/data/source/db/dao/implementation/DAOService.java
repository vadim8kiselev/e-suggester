package com.kiselev.suggester.data.source.db.dao.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.source.db.dao.DAO;
import com.kiselev.suggester.data.source.db.repository.ProductRepository;
import com.kiselev.suggester.data.source.db.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DAOService implements DAO {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    private boolean fallback = true;

    @Override
    public void profile(Profile profile) {
        try {
            if (this.fallback) {
                if (!isDeactivated(profile)) {
                    profileRepository.save(profile);
                }
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }
    }

    @Override
    public void profiles(List<Profile> profiles) {
        try {
            if (this.fallback) {
                profileRepository.save(filterDeactivatedProfiles(profiles));
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }
    }

    @Override
    public Profile profile(String id) {
        try {
            if (this.fallback) {
                return profileRepository.findOne(id);
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }

        return null;
    }

    @Override
    public List<Profile> profiles() {
        try {
            if (this.fallback) {
                return Lists.newArrayList(profileRepository.findAll());
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }

        return Lists.newArrayList();
    }

    @Override
    public void product(Product product) {
        try {
            if (this.fallback) {
                productRepository.save(product);
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }
    }

    @Override
    public void products(List<Product> products) {
        try {
            if (this.fallback) {
                productRepository.save(products);
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }
    }

    @Override
    public Product product(String id) {
        try {
            if (this.fallback) {
                return productRepository.findOne(id);
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }

        return null;
    }

    @Override
    public List<Product> products() {
        try {
            if (this.fallback) {
                return Lists.newArrayList(productRepository.findAll());
            }
        } catch (Exception exception) {
            //this.fallback = false;
        }

        return Lists.newArrayList();
    }

    private Iterable<Profile> filterDeactivatedProfiles(Iterable<Profile> profiles) {
        return Lists.newArrayList(profiles).stream()
                .filter(this::isDeactivated)
                .collect(Collectors.toList());
    }

    private boolean isDeactivated(Profile profile) {
        return profile.getDeactivated() == null || profile.getDeactivated().isEmpty();
    }
}
