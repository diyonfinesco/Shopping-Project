package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.dto.product.UpdateProductDTO;
import com.example.demo.utils.FileUploadUtil;
import io.micrometer.core.annotation.Timed;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.product.CreateProductDTO;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Map<String,Object>> getAllProduct(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10    ") int size) {
        var products = this.productService.readAllProduct(page,size);
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
