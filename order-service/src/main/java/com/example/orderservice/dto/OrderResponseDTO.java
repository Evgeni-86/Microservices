package com.example.orderservice.dto;

import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderResponseDTO {
    private String number;
    private List<OrderItemResponseDTO> orderItemsList;
}
