package com.electronic_shop_tvo.electronicshoptvo.model.dto.purchase;

import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemDto {
    private Integer itemId;
    private Integer quantity;

    public PurchaseItemDto(PurchaseItem purchaseItem) {
        this.itemId = purchaseItem.getItemId();
        this.quantity = purchaseItem.getQuantity();
    }

    public PurchaseItem toDomain() {
        return new PurchaseItem(
                this.itemId,
                this.quantity
        );
    }
}
