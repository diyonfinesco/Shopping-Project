package com.example.demo.service;

import java.io.IOException;
import java.util.*;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImlpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product, MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "dqpj2fiob");
        config.put("api_key", "975587998467626");
        config.put("api_secret", "Y67BhHYXNfj0Cazk-m3J5BxbyP0");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", product.getName()));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        String url = cloudinary.url().transformation(new Transformation().width(1000).height(1223).crop("fill")).generate(product.getName());
        product.setImagePath(url);
        return this.productRepository.save(product);
    }

    @Override
    public Map<String, Object> readAllProduct(int page, int size) {
        var response = new HashMap<String, Object>();

        response.put("page",page);
        response.put("size",size);
        response.put("totalItems", productRepository.count());
        response.put("products", this.productRepository.findAll(PageRequest.of(--page,size)).getContent());

        return response;
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
    }

    @Override
    public Product updateProduct(String id, Product product) {
        var p = getProductById(id);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());
        this.productRepository.save(p);
        return p;
    }

    @Override
    public void deleteProduct(String id) {
        var product = this.getProductById(id);
        this.productRepository.delete(product);
    }
}
