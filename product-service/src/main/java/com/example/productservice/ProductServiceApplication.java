package com.example.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner load(@Autowired MongoTemplate mongoTemplate) {
        return (e) -> {
            for (String collectionName : mongoTemplate.getCollectionNames()) {
                if (!collectionName.startsWith("system.")) {
                    mongoTemplate.getCollection(collectionName).drop();
                }
            }
        };
    }
}
