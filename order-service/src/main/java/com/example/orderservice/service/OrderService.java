package com.example.orderservice.service;

import com.example.orderservice.dto.InventoryResponseDTO;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderItemResponseDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderService(@Autowired OrderRepository orderRepository,
                        @Autowired WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setNumber(UUID.randomUUID().toString());

        List<OrderItem> orderItemList = orderDTO.getOrderItemsList()
                .stream().map(el -> OrderItem.builder()
                        .code(el.getCode())
                        .quantity(el.getQuantity())
                        .price(el.getPrice())
                        .order(order)
                        .build())
                .toList();

        order.setOrderItemsList(orderItemList);

        List<String> list = order.getOrderItemsList().stream().map(el -> el.getCode()).toList();

        InventoryResponseDTO[] inventoryResponseDTOS = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("code", list).build())
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();

        boolean allProductsIsPresentInStock = Arrays.stream(inventoryResponseDTOS).allMatch(el -> el.isPresent());

        if (allProductsIsPresentInStock) orderRepository.save(order);
        else throw new IllegalArgumentException("Product not present in stock");
    }
}
