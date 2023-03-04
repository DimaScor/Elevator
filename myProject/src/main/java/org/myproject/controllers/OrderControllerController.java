package org.myproject.controllers;

import org.myproject.entities.Order;
import org.springframework.web.bind.annotation.*;
import org.myproject.repositories.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderControllerController {
    private final OrderRepository orderRepository;

    public OrderControllerController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

}