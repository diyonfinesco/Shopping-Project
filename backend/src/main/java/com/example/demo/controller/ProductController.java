package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.product.UpdateProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    // update
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable String id,@Valid @RequestBody UpdateProductDTO productDTO) {
        var product = this.productService.updateProduct(id,modelMapper.map(productDTO, Product.class));
        return ResponseEntity.ok(product);
    }

    // delete
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
