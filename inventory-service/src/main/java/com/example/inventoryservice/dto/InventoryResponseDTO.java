package com.example.inventoryservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
