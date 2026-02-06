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
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int id;
    private String title;
    private BigDecimal price;
    private LocalDateTime producingYear;
    private String manufacturer;
    private int quantity;
    private int itemTypeId;

    public Item(ItemDto itemDto) {
        this.title = itemDto.title();
        this.price = itemDto.price();
        this.producingYear = itemDto.producingYear();
        this.manufacturer = itemDto.manufacturer();
        this.quantity = itemDto.quantity();
        this.itemTypeId = itemDto.itemTypeId();
    }

    public Item(CreateItemDto createItemDto) {
        this.title = createItemDto.title();
        this.price = createItemDto.price();
        this.producingYear = createItemDto.producingYear();
        this.manufacturer = createItemDto.manufacturer();
        this.quantity = createItemDto.quantity();
        this.itemTypeId = createItemDto.itemTypeId();
    }

    public Item(UpdateItemDto updateItemDto) {
        this.title = updateItemDto.title();
        this.price = updateItemDto.price();
        this.quantity = updateItemDto.quantity();
        this.itemTypeId = updateItemDto.itemTypeId();
    }
}
