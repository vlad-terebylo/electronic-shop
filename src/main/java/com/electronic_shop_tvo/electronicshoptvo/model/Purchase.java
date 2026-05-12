package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase p)) return false;
        return Objects.equals(id, p.id)
                && Objects.equals(email, p.email)
                && Objects.equals(cardNumber, p.cardNumber)
                && Objects.equals(purchaseItems, p.purchaseItems)
                && totalPrice.compareTo(p.totalPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, cardNumber, totalPrice);
    }
}
