package com.example.orderservice.web.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        return "Order placed";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrderResponseDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
}
