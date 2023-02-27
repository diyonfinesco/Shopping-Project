package com.example.demo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

@Document
public class Order {
    @Id
    private String id;

    @NotNull
    private List<OrderItem> items;

    @NotNull
    private String status = "PENDING";

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
