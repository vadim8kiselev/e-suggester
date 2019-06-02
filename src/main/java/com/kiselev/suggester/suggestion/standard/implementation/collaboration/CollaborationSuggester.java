package com.kiselev.suggester.suggestion.standard.implementation.collaboration;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.model.entity.annotation.Listed;
import com.kiselev.suggester.data.model.entity.annotation.Unique;
import com.kiselev.suggester.data.source.db.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollaborationSuggester {

    @Value("${collaborative.threshold:0.7}")
    private Double threshold;

    @Value("${collaborative.global.limit:1000}")
    private Integer globalLimit;

    @Value("${collaborative.limit:0}")
    private Integer limit;

    @Autowired
    private DAO dao;

    public List<Product> suggest(Profile client) {
        List<Profile> profiles = dao.profiles();

        profiles.remove(client);

        List<Profile> similarProfiles = profiles.stream()
                .filter(profile -> !profile.getProducts().isEmpty())
                .filter(profile -> calculateProfileSimilarity(profile, client) >= threshold) // Performance is low, better compose a map
                .sorted(Comparator.<Profile>comparingDouble(o -> calculateProfileSimilarity(o, client)).reversed())
                .collect(Collectors.toList());

        Set<Product> products = similarProfiles.stream()
                .map(Profile::getProducts)
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        products.removeAll(client.getProducts());

        return products.stream()
                .limit(limit > 0 ? Math.min(limit, globalLimit) : globalLimit)
                .collect(Collectors.toList());
    }

    private Double calculateProfileSimilarity(Profile profile, Profile client) {
        Field[] fields = Profile.class.getDeclaredFields();

        Integer numberOfTotalParameters = fields.length;
        Integer numberOfSimilarParameters = 0;

        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object profileValue = ReflectionUtils.getField(field, profile);
            Object clientValue = ReflectionUtils.getField(field, client);

            if (field.isAnnotationPresent(Unique.class)) {
                numberOfTotalParameters--;

            } else if (field.isAnnotationPresent(Listed.class)) {
                if (field.getType().equals(String.class)) {
                    String profileStringValue = (String) profileValue;
                    String clientStringValue = (String) clientValue;

                    if (stringsEqualIgnoreCase(profileStringValue, clientStringValue)) {
                        numberOfSimilarParameters++;
                    } else {
                        Set<String> profileValues = Stream.of(profileStringValue.toLowerCase().split("[,.;/\\\\]"))
                                .map(String::trim)
                                .collect(Collectors.toSet());

                        Set<String> clientValues = Stream.of(clientStringValue.toLowerCase().split("[,.;/\\\\]"))
                                .map(String::trim)
                                .collect(Collectors.toSet());

                        if (!Sets.intersection(profileValues, clientValues).isEmpty()) {
                            numberOfSimilarParameters++;
                        }
                    }
                } else {
                    throw new RuntimeException("Listed field should be String typed! [" + field.getName() + "]");
                }
            } else if (field.getType().equals(String.class)) {
                String profileStringValue = (String) profileValue;
                String clientStringValue = (String) clientValue;

                if (stringsEqualIgnoreCase(profileStringValue, clientStringValue)) {
                    numberOfSimilarParameters++;
                }
            } else if (field.getType().equals(Set.class)) {
                Set<Product> profileSetValue = toProductsSet(profileValue);
                Set<Product> clientSetValue = toProductsSet(clientValue);

                if (!Sets.intersection(profileSetValue, clientSetValue).isEmpty()) {
                    numberOfSimilarParameters++;
                }
            } else {
                if (Objects.equal(profileValue, clientValue)) {
                    numberOfSimilarParameters++;
                }
            }
        }

        return (numberOfSimilarParameters + 0.0) / numberOfTotalParameters;
    }

    @SuppressWarnings("all")
    private boolean stringsEqualIgnoreCase(String string, String gnirts) {
        return string == gnirts || string != null && string.equalsIgnoreCase(gnirts);
    }

    @SuppressWarnings("unchecked")
    private Set<Product> toProductsSet(Object value) {
        return (Set<Product>) value;
    }
}
