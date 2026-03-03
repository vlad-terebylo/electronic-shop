package com.electronic_shop_tvo.electronicshoptvo.unit;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import com.electronic_shop_tvo.electronicshoptvo.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
        String card = "675831-1-4567889";
        List<Purchase> expectedPurchases = new ArrayList<>();

        PurchaseItem purchaseItem = new PurchaseItem(1, 5);
        PurchaseItem secondPurchaseItem = new PurchaseItem(2, 5);

        expectedPurchases.add(new Purchase(
                1,
                "terebilo.vlad1409@gmail.com",
                card,
                List.of(purchaseItem, secondPurchaseItem),
                BigDecimal.valueOf(1000)));
        when(purchaseRepository.getAllPurchases()).thenReturn(expectedPurchases);

        List<Purchase> result = purchaseService.getAllPurchases();

        assertEquals(expectedPurchases, result);
    }

    @Test
    void should_return_purchase_by_id() {
        int id = 1;
        PurchaseItem purchaseItem = new PurchaseItem(1, 5);
        PurchaseItem secondPurchaseItem = new PurchaseItem(2, 5);

        Purchase expectedPurchase = new Purchase(
                id,
                "terebilo.vlad1409@gmail.com",
                "675831-1-4567889",
                List.of(purchaseItem, secondPurchaseItem),
                BigDecimal.valueOf(1000));

        when(purchaseRepository.getPurchaseById(id)).thenReturn(expectedPurchase);

        Purchase result = purchaseService.getPurchaseById(id);

        assertEquals(expectedPurchase, result);
    }

    @Test
    void should_add_new_purchase() {
        PurchaseItem purchaseItem = new PurchaseItem(1, 5);
        PurchaseItem secondPurchaseItem = new PurchaseItem(2, 5);

        Purchase purchase = new Purchase(1,
                "terebilo.vlad1409@gmail.com",
                "675831-1-4567889",
                List.of(purchaseItem, secondPurchaseItem),
                BigDecimal.valueOf(1000));

        purchaseService.addNewPurchase(purchase);

        verify(purchaseRepository, times(1)).save(purchase);
    }
}
