package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServiceImlpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> readAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order updateOrder(String id, String status) {
        if (!status.equalsIgnoreCase("SHIPPED"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order status!");

        Optional<Order> orderDB = this.orderRepository.findById(id);

        if (orderDB.isPresent()) {
            Order updatedOrder = orderDB.get();

            updatedOrder.setStatus("SHIPPED");
            this.orderRepository.save(updatedOrder);
            return updatedOrder;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
    }
}
