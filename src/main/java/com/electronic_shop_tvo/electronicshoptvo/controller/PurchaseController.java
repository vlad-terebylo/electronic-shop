package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.dto.purchase.CreatePurchaseDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.purchase.PurchaseDto;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<PurchaseDto> getAllPurchases() {
        log.info("Getting all purchases");

        return purchaseService.getAllPurchases().stream()
                .map(PurchaseDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public PurchaseDto getPurchaseById(@PathVariable int id) {
        log.info("Getting a purchase by id: {}", id);

        return new PurchaseDto(this.purchaseService.getPurchaseById(id));
    }

    @PostMapping
    public void addNewPurchases(@Valid @RequestBody CreatePurchaseDto createPurchaseDto) {
        log.info("Adding a new purchase");

        this.purchaseService.addNewPurchase(createPurchaseDto.toDomain());
    }

}
