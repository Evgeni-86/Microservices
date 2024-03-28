package com.example.inventoryservice;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner load(@Autowired InventoryRepository inventoryRepository) {
        return (e) -> {
            Inventory inventory1 = Inventory.builder()
                    .code("444")
                    .quantity(15)
                    .build();
            Inventory inventory2 = Inventory.builder()
                    .code("555")
                    .quantity(0)
                    .build();
            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
        };
    }
}
