package com.example.productservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
}