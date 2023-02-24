package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImlpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> readAllProduct(String category) {
        if (category != null)
            return this.productRepository.findByCategory(category.toUpperCase());
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
    }
}
