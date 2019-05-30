package com.kiselev.suggester.data.source.db.repository;

import com.kiselev.suggester.data.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByType(String type);
}
