package com.electronic_shop_tvo.electronicshoptvo.unit;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    void should_return_all_purchases() {
        List<Purchase> expectedPurchases = new ArrayList<>();

        expectedPurchases.add(new Purchase(1, "terebilo.vlad1409@gmail.com", "675831-1-4567889", List.of(2, 3)));

        when(purchaseRepository.getAllPurchases()).thenReturn(expectedPurchases);

        List<Purchase> result = purchaseService.getAllPurchases();

        assertEquals(expectedPurchases, result);
    }

    @Test
    void should_return_purchase_by_id() {
        int id = 1;
        Purchase expectedPurchase = new Purchase(id, "terebilo.vlad1409@gmail.com", "675831-1-4567889", List.of(2, 3));

        when(purchaseRepository.getPurchaseById(id)).thenReturn(expectedPurchase);

        Purchase result = purchaseService.getPurchaseById(id);

        assertEquals(expectedPurchase, result);
    }

    @Test
    void should_return_purchases_by_card() {
        String card = "675831-1-4567889";
        List<Purchase> expectedPurchases = new ArrayList<>();

        expectedPurchases.add(new Purchase(
                1,
                "terebilo.vlad1409@gmail.com",
                card, List.of(2, 3)));

        when(purchaseRepository.getPurchasesByCard(card)).thenReturn(expectedPurchases);

        List<Purchase> result = purchaseService.getPurchasesByCard(card);

        assertEquals(expectedPurchases, result);
    }

    @Test
    void should_return_purchases_by_email() {
        String email = "terebilo.vlad1409@gmail.com";
        List<Purchase> expectedPurchases = new ArrayList<>();

        expectedPurchases.add(new Purchase(
                1,
                email,
                "675831-1-4567889", List.of(2, 3)));

        when(purchaseRepository.getPurchasesByEmail(email)).thenReturn(expectedPurchases);

        List<Purchase> result = purchaseService.getPurchasesByEmail(email);

        assertEquals(expectedPurchases, result);
    }

    @Test
    void should_add_new_purchase() {
        Purchase purchase = new Purchase(1, "terebilo.vlad1409@gmail.com", "675831-1-4567889", List.of(2, 3));

        purchaseService.addNewPurchase(purchase);

        verify(purchaseRepository, times(1)).save(purchase);
    }
}
