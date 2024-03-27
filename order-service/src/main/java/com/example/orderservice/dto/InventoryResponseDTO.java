package com.example.orderservice.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InventoryResponseDTO {
    private String code;
    private boolean isPresent;
}
