package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.order.CreateOrderDTO;
import com.example.demo.dto.order.UpdateOrderDTO;
import com.example.demo.models.Order;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        Order orderRequest = modelMapper.map(createOrderDTO, Order.class);
        return this.orderService.createOrder(orderRequest);
    }

    // real all
    @GetMapping()
    public List<Order> getAllOrders() {
        return this.orderService.readAllOrders();
    }

    // read one
    @GetMapping(value = "/{id}")
    public Order getOrder(@PathVariable String id) {
        return this.orderService.getOrderById(id);
    }

    // update
    @PutMapping(value = "/{id}")
    public Order updateOrder(@Valid @PathVariable String id, @RequestBody UpdateOrderDTO updateOrderDTO) {
        Order orderRequest = modelMapper.map(updateOrderDTO, Order.class);
        return this.orderService.updateOrder(id, orderRequest);
    }

    // delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable String id) {
        this.orderService.deleteOrder(id);
    }
}
