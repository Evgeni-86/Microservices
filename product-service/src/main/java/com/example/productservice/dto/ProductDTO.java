package com.example.productservice.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
}
