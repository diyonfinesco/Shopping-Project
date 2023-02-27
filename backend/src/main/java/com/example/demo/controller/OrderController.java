package com.example.demo.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.dto.order.CreateOrderDTO;
import com.example.demo.dto.order.UpdateOrderDTO;
import com.example.demo.models.Order;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;

@RestController()
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    // create
    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        this.orderService.createOrder(modelMapper.map(createOrderDTO, Order.class));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // real all
    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders() {
        var orders = this.orderService.readAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable String id,
            @RequestBody @Valid UpdateOrderDTO updateOrderDTO) {
        this.orderService.updateOrder(id, updateOrderDTO.getStatus());
        return ResponseEntity.ok().build();
    }
}
