package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class Item {

    private static long counter = 0;
    private long id;
    private String title;
    private BigDecimal price;
    private final int producingYear;
    private final String manufacturer;
    private int quantity;
    private int itemTypeId;


    public Item(String title, BigDecimal price, int producingYear, String manufacturer, int quantity, int itemTypeId) {
        this.id = ++counter;
        this.title = title;
        this.price = price;
        this.producingYear = producingYear;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.itemTypeId = itemTypeId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Item item = (Item) object;
        return Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
