package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.Data;

import java.util.Objects;

@Data
public class PurchaseItem {
    private static long counter = 0;
    private long id;
    private long purchaseId;
    private long itemId;

    public PurchaseItem(long purchaseId, long itemId) {
        this.id = ++counter;
        this.purchaseId = purchaseId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PurchaseItem that = (PurchaseItem) object;
        return purchaseId == that.purchaseId && itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, itemId);
    }
}
