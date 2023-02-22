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
    public Order getOrderById(String id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
    }

    @Override
    public Order updateOrder(String id, Order order) {
        Optional<Order> orderDB = this.orderRepository.findById(id);

        if (orderDB.isPresent()) {
            Order updatedOrder = orderDB.get();
            System.out.println(order.getStatus());
            

            updatedOrder.setCustomerName(order.getCustomerName());
            updatedOrder.setShippingAddress(order.getShippingAddress());
            updatedOrder.setItems(order.getItems());
            updatedOrder.setStatus(order.getStatus());
            this.orderRepository.save(updatedOrder);

            return updatedOrder;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
    }

    @Override
    public void deleteOrder(String id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            this.orderRepository.delete(order.get());
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found");
    }

}
