package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.product.ProductDTO;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody ProductDTO productDTO) {
        Product productRequest = modelMapper.map(productDTO, Product.class);
        return this.productService.createProduct(productRequest);
    }

    // real all
    @GetMapping()
    public List<Product> getAllProduct(@RequestParam("category") Optional<String> category) {
        String categoryName = category.orElse(null);
        return this.productService.readAllProduct(categoryName);
    }

    // read one
    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable String id) {
        return this.productService.getProductById(id);
    }

    // update
    @PutMapping(value = "/{id}")
    public Product updateProduct(@Valid @PathVariable String id, @RequestBody ProductDTO productDTO) {
        Product productRequest = modelMapper.map(productDTO, Product.class);
        return this.productService.updateProduct(id, productRequest);
    }

    // delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable String id) {
        this.productService.deleteProduct(id);
    }
}
