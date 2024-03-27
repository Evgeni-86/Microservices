package com.example.inventoryservice.web.controller;

import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(@Autowired InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{code}")
    public boolean isPresent(@PathVariable("code") String code) {
        return inventoryService.isPresent(code);
    }
}
