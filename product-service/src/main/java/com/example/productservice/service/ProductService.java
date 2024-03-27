package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream()
                .map(el -> ProductResponseDTO.builder()
                        .id(el.getId())
                        .name(el.getName())
                        .description(el.getDescription())
                        .price(el.getPrice())
                        .build()).toList();
    }
}
