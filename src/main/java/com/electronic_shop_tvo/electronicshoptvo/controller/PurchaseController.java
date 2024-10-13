package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return this.purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable int id) {
        return this.purchaseService.getPurchaseById(id);
    }

    @GetMapping("/card/{cardNumber}")
    public List<Purchase> getPurchasesByCard(@PathVariable String cardNumber) {
        return this.purchaseService.getPurchasesByCard(cardNumber);
    }

    @GetMapping("/email/{email}")
    public List<Purchase> getPurchasesByEmail(@PathVariable String email) {
        return this.purchaseService.getPurchasesByEmail(email);
    }

    @PostMapping
    public void addNewPurchases(@RequestBody Purchase purchase) {
        this.purchaseService.addNewPurchase(purchase);
    }

}
