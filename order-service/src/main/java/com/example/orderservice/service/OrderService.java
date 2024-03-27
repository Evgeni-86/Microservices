package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderItemResponseDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(@Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
                        .build()).toList();

        order.setOrderItemsList(orderItemList);
        orderRepository.save(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(el -> OrderResponseDTO.builder()
                        .number(el.getNumber())
                        .orderItemsList(toOrderItemResponseDTO(el.getOrderItemsList()))
                        .build())
                .toList();
    }
    private List<OrderItemResponseDTO> toOrderItemResponseDTO(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(el -> OrderItemResponseDTO.builder()
                        .code(el.getCode())
                        .quantity(el.getQuantity())
                        .price(el.getPrice())
                        .build())
                .toList();
    }
}
