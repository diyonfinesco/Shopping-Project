package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import com.example.demo.dto.product.UpdateProductDTO;
import io.micrometer.core.annotation.Timed;
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
    @Timed
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> create(@Valid @ModelAttribute CreateProductDTO productDTO) throws IOException {
        this.productService.createProduct(modelMapper.map(productDTO, Product.class),productDTO.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // real all
    @GetMapping()
    public ResponseEntity<Map<String,Object>> getAllProduct(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String category) {
        var products = this.productService.readAllProduct(page,size,category);
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
    @Timed
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable String id,@Valid @ModelAttribute UpdateProductDTO productDTO) throws IOException {
        var product = this.productService.updateProduct(id,modelMapper.map(productDTO, Product.class),productDTO.getImage());
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
