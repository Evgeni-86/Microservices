package com.example.inventoryservice.web.controller;

import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<InventoryResponseDTO> isCodesPresent(@RequestParam("code") List<String> code) {
        return inventoryService.getInventaryList(code);
    }
}
