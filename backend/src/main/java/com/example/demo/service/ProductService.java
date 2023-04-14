package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.models.Product;

public interface ProductService {
    public Product createProduct(Product product,String filepath);

    public Map<String,Object> readAllProduct(int page, int size);

    public Product getProductById(String id);

    public Product updateProduct(String id, Product product);

    public void deleteProduct(String id);
}
