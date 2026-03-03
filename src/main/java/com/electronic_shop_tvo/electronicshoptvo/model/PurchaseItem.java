package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {
    private Integer itemId;
    private Integer quantity;
}
