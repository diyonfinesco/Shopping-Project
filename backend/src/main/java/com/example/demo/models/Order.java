package com.example.demo.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

enum STATUS {
    PENDING,
    SHIPPED,
}

@Document
public class Order {
    @Id
    private String id;

    @NotNull
    private String customerName;

    @NotNull
    private String shippingAddress;

    @NotNull
    private List<OrderItem> items;

    @NotNull
    private STATUS status = STATUS.PENDING;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public STATUS getStatus() {
        return this.status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
