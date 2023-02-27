package com.example.demo.dto.order;

import java.util.List;

import com.example.demo.models.OrderItem;

import jakarta.validation.constraints.NotNull;

public class CreateOrderDTO {
    @NotNull
    private List<OrderItem> items;

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
