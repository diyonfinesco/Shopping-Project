package com.example.demo.repository;

import com.example.demo.models.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCategory(String category, Pageable pageable);
}
