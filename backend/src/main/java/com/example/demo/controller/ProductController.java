package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.product.CreateProductDTO;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductDTO productDTO) {
        this.productService.createProduct(modelMapper.map(productDTO, Product.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // real all
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam("category") Optional<String> category) {
        var products = this.productService.readAllProduct(category.orElse(null));
        return ResponseEntity.ok(products);
    }

    // read one
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        var product = this.productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
