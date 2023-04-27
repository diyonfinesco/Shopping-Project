package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import com.example.demo.models.Order;

public interface OrderService {
    public Order createOrder(Order order) throws IOException;

    public List<Order> readAllOrders();

    public Order updateOrder(String id, String status);
}
