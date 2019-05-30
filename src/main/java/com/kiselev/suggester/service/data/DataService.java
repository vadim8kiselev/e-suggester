package com.kiselev.suggester.service.data;

import com.kiselev.suggester.data.model.entity.Product;
import com.kiselev.suggester.data.source.db.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataService {

    @Value("${default.limit:100}")
    private Integer limit;

    @Autowired
    private DAO dao;

    public List<Product> retrieveRandomRecommendaton() {
        List<Product> products = dao.products();
        Collections.shuffle(products);

        return products.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
