package com.example.orderservice.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemResponseDTO {
    private String code;
    private Integer quantity;
    private Double price;
}
