package com.example.demo.service;

import java.util.List;
import com.example.demo.models.Order;

public interface OrderService {
    public Order createOrder(Order product);

    public List<Order> readAllOrders();

    public Order getOrderById(String id);

    public Order updateOrder(String id, Order product);

    public void deleteOrder(String id);
}
