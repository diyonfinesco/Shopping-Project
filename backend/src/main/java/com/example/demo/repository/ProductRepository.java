package com.example.demo.repository;

import com.example.demo.models.Product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.security.access.prepost.PreAuthorize;

// @PreAuthorize("hasRole('ROLE_USER')")
public interface ProductRepository extends MongoRepository<Product, String> {

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @Override
    // <S extends Product> S save(S entity);

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // @Override
    // void delete(Product product);

    List<Product> findByCategory(String category);
}
