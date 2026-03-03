package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;


@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private int id;
    private String email;
    private String cardNumber;
    private List<PurchaseItem> purchaseItems;
    private BigDecimal totalPrice;

    public Purchase(String email, String cardNumber, List<PurchaseItem> purchaseItems) {
        this.email = email;
        this.cardNumber = cardNumber;
        this.purchaseItems = purchaseItems;
    }
}
