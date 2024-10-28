package com.electronic_shop_tvo.electronicshoptvo.integration;

import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.ItemTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.PurchaseTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseServiceTest extends BaseTest {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseTestRepository purchaseTestRepository;

    @Autowired
    private ItemTestRepository itemTestRepository;

    @BeforeEach
    void cleanUp() {
        purchaseTestRepository.clear();
        itemTestRepository.clear();
    }

    @Test
    void should_return_all_purchases() {
        List<Purchase> result = purchaseService.getAllPurchases();
        assertEquals(purchaseTestRepository.getAllPurchases(), result);
    }


    @Test
    void should_return_purchase_by_id() {
        int id = 1;

        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        Purchase purchase = new Purchase(id, "terebilo.vlad1409@gmail.com", "675831-1-4567889", List.of(id));

        itemTestRepository.addNewItem(item);
        purchaseService.addNewPurchase(purchase);

        Purchase result = purchaseService.getPurchaseById(id);
        assertEquals(purchase, result);
    }

    @Test
    void should_return_purchases_by_card() {
        int id = 1;
        String card = "675831-1-4567889";

        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        Purchase purchase = new Purchase(id, "terebilo.vlad1409@gmail.com", card, List.of(id));

        itemTestRepository.addNewItem(item);
        purchaseService.addNewPurchase(purchase);

        List<Purchase> result = purchaseService.getPurchasesByCard(card);
        assertEquals(purchase, result.get(0));
    }

    @Test
    void should_return_purchases_by_email() {
        int id = 1;
        String email = "terebilo.vlad1409@gmail.com";

        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        Purchase purchase = new Purchase(id, email, "675831-1-4567889", List.of(id));

        itemTestRepository.addNewItem(item);
        purchaseService.addNewPurchase(purchase);

        List<Purchase> result = purchaseService.getPurchasesByEmail(email);
        assertEquals(purchase, result.get(0));
    }

    @Test
    void should_add_new_purchase() {
        Item item = new Item(1, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        Purchase purchase = new Purchase(1, "terebilo.vlad1409@gmail.com", "675831-1-4567889", List.of(1));

        itemTestRepository.addNewItem(item);
        purchaseService.addNewPurchase(purchase);

        assertTrue(purchaseTestRepository.getAllPurchases().contains(purchase));
    }
}
