package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.example.demo.utils.FileUploadConfig;
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
    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public Product createProduct(Product product, MultipartFile file) throws IOException {
        String uploadDir = fileUploadConfig.getDirectory();
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String originalFilename =  + new Date().getTime() + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(originalFilename);
        Files.copy(file.getInputStream(),filePath);

        product.setImagePath(originalFilename);
        return this.productRepository.save(product);
    }

    @Override
    public Map<String, Object> readAllProduct(int page, int size,String category) {
        var response = new HashMap<String, Object>();

        response.put("page",page);
        response.put("size",size);
        response.put("totalItems", productRepository.count());

        if(category.equals("")){
            response.put("products", this.productRepository.findAll(PageRequest.of(--page,size)).getContent());
        }else {
            response.put("products", this.productRepository.findAllByCategory(category,PageRequest.of(--page,size)));
        }

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
    public Product updateProduct(String id, Product product, MultipartFile file) throws IOException {
        var p = getProductById(id);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());
        p.setQuantity(product.getQuantity());

        if(file != null){
            String uploadDir = fileUploadConfig.getDirectory();
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            String originalFilename =  + new Date().getTime() + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(originalFilename);
            Files.copy(file.getInputStream(),filePath);
            Files.deleteIfExists(Path.of(uploadPath + "/" + p.getImagePath()));
            p.setImagePath(originalFilename);
        }
        this.productRepository.save(p);
        return p;
    }

    @Override
    public void deleteProduct(String id) {
        var product = this.getProductById(id);
        this.productRepository.delete(product);
    }
}
