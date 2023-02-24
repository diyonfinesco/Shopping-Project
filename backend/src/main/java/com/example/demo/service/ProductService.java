package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Product;

public interface ProductService {
    public Product createProduct(Product product);

    public List<Product> readAllProduct(String category);

    public Product getProductById(String id);
}
