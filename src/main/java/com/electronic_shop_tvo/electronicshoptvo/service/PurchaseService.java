package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return this.purchaseRepository.getAllPurchases();
    }

    public Purchase getPurchaseById(int id) {
        return this.purchaseRepository.getPurchaseById(id);
    }

    public List<Purchase> getPurchasesByCard(String cardNumber) {
        return this.purchaseRepository.getPurchasesByCard(cardNumber);
    }

    public List<Purchase> getPurchasesByEmail(String email) {
        return this.purchaseRepository.getPurchasesByEmail(email);
    }

    public void addNewPurchase(Purchase purchase) {
        this.purchaseRepository.save(purchase);
    }
}
