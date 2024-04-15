package com.example.orderservice.web.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/order")
@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
@TimeLimiter(name = "inventory")
@Retry(name = "inventory")
public class OrderController {

    private final OrderService orderService;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody OrderDTO orderDTO) {
        return CompletableFuture.supplyAsync(() -> orderService.createOrder(orderDTO));
    }

    public CompletableFuture<String> fallbackMethod(OrderDTO orderDTO, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Please try later");
    }
}
