package com.example.demo.dto.order;

import java.util.List;

import com.example.demo.models.OrderItem;

import jakarta.validation.constraints.NotNull;

public class UpdateOrderDTO {
    @NotNull
    private String customerName;

    @NotNull
    private String shippingAddress;

    @NotNull
    private List<OrderItem> items;

    @NotNull
    private String status;

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
