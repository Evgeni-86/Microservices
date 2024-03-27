package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponseDTO;
import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(@Autowired InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public boolean isPresent(String code) {
        Optional<Inventory> byCode = inventoryRepository.findByCode(code);
        return byCode.isPresent();
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventaryList(List<String> code) {
        List<Inventory> byCodeIn = inventoryRepository.findByCodeIn(code);
        return byCodeIn.stream()
                .map(el -> InventoryResponseDTO.builder()
                        .code(el.getCode())
                        .isPresent(el.getQuantity() > 0)
                        .build())
                .toList();
    }
}
