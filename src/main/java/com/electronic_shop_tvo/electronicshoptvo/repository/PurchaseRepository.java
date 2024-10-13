package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;

import java.util.List;

public interface PurchaseRepository {

    List<Purchase> getAllPurchases();

    Purchase getPurchaseById(int id);

    List<Purchase> getPurchasesByCard(String cardNumber);

    List<Purchase> getPurchasesByEmail(String email);

    void save(Purchase purchase);
}
