package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.demo.models.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public Product createProduct(Product product, MultipartFile file) throws IOException;

    public Map<String,Object> readAllProduct(int page, int size,String category);

    public Product getProductById(String id);

    public Product updateProduct(String id, Product product, MultipartFile file) throws IOException;

    public void deleteProduct(String id);
}
