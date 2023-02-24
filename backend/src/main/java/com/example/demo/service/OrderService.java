package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Order;

public interface OrderService {
    public Order createOrder(Order product);

    public List<Order> readAllOrders();

    public Order updateOrder(String id, String status);
}
