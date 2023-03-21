package com.example.demo.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 50)
    private String description;

    @NotNull
    @Size(max = 15)
    private String category;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    @Min(0)
    private int quantity;

    public Product() {

    }

    public Product(String name, String description, String category, int price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
