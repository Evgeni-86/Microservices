package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderItem;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO {
    private List<OrderItemDTO> orderItemsList;
}
