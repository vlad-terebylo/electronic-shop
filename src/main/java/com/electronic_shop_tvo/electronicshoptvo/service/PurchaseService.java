package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, ItemRepository itemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
    }

    public List<Purchase> getAllPurchases() {
        return this.purchaseRepository.getAllPurchases();
    }

    public Purchase getPurchaseById(int id) {
        return this.purchaseRepository.getPurchaseById(id);
    }

    public void addNewPurchase(Purchase purchase) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
            int id = purchaseItem.getItemId();
            Item item = itemRepository.getItemById(id);
            BigDecimal price = item.getPrice();
            int quantity = purchaseItem.getQuantity();

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(itemTotal);

            itemRepository.updateQuantity(id, item.getQuantity() - quantity);
        }

        purchase.setTotalPrice(totalPrice);
        this.purchaseRepository.save(purchase);
    }
}
