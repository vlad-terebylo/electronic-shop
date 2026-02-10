package com.electronic_shop_tvo.electronicshoptvo.model;

import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.CreateItemDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.ItemDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.UpdateItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private int id;
    private String title;
    private BigDecimal price;
    private LocalDateTime producingYear;
    private String manufacturer;
    private int quantity;
    private int itemTypeId;

    public Item(String title,
                BigDecimal price,
                LocalDateTime producingYear,
                String manufacturer,
                int quantity,
                int itemTypeId) {
        this.title = title;
        this.price = price;
        this.producingYear = producingYear;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.itemTypeId = itemTypeId;
    }

    public Item(String title,
                BigDecimal price,
                int quantity,
                int itemTypeId) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.itemTypeId = itemTypeId;
    }
}
