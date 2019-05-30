package com.kiselev.suggester.suggestion.standard.implementation.content;

import com.google.common.base.Objects;
import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.model.entity.annotation.Ranged;
import com.kiselev.suggester.data.model.entity.annotation.Unique;
import com.kiselev.suggester.data.source.db.dao.DAO;
import org.nd4j.linalg.io.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContentBasedSuggester {

    @Value("${content.based.threshold:0.8}")
    private Double threshold;

    @Value("${content.based.global.limit:1000}")
    private Integer globalLimit;

    @Value("${content.based.limit:0}")
    private Integer limit;

    @Autowired
    private DAO dao;

    public List<Product> suggest(Profile profile) {
        List<Product> products = dao.products();
        Set<Product> clientProducts = profile.getProducts();

        products.removeAll(clientProducts);

        Set<Product> sortedProducts = products.stream()
                .filter(product -> calculateProductSimilarity(product, clientProducts) >= threshold)
                .sorted(Comparator.<Product>comparingDouble(o -> calculateProductSimilarity(o, clientProducts)).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return sortedProducts.stream()
                .limit(limit > 0 ? Math.min(limit, globalLimit) : globalLimit)
                .collect(Collectors.toList());
    }

    private Double calculateProductSimilarity(Product product, Set<Product> clientProducts) {
        return clientProducts.stream()
                .mapToDouble(clientProduct -> calculateProductSimilarity(product, clientProduct))
                .max().orElse(0.0);
    }

    private Double calculateProductSimilarity(Product product, Product clientProduct) {
        Field[] fields = Product.class.getDeclaredFields();

        Integer numberOfTotalParameters = fields.length;
        Integer numberOfSimilarParameters = 0;

        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object profileValue = ReflectionUtils.getField(field, product);
            Object clientValue = ReflectionUtils.getField(field, clientProduct);

            if (field.isAnnotationPresent(Unique.class)) {
                numberOfTotalParameters--;

            } else if (field.isAnnotationPresent(Ranged.class)) {
                if (field.getType().equals(Integer.class)) {
                    Ranged ranged = field.getAnnotation(Ranged.class);

                    Integer profileIntegerValue = (Integer) profileValue;
                    Integer clientIntegerValue = (Integer) clientValue;


                    if (ranged.nullable()) {
                        if (Math.abs(profileIntegerValue - clientIntegerValue) <= ranged.value()) {
                            numberOfSimilarParameters++;
                        }
                    } else {
                        if (profileIntegerValue != 0 && clientIntegerValue != 0) {
                            if (Math.abs(profileIntegerValue - clientIntegerValue) <= ranged.value()) {
                                numberOfSimilarParameters++;
                            }
                        }
                    }

                } else {
                    throw new RuntimeException("Ranged field should be Integer typed! [" + field.getName() + "]");
                }
            } else if (field.getType().equals(String.class)) {
                String profileStringValue = (String) profileValue;
                String clientStringValue = (String) clientValue;

                if (stringsEqualIgnoreCase(profileStringValue, clientStringValue)) {
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
}
