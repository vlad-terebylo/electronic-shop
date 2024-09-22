package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.Data;

import java.util.Objects;

@Data
public class ItemType {

    private static long counter = 0;
    private long id;
    private String name;

    public ItemType(String name) {
        this.id = ++counter;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemType itemType = (ItemType) object;
        return Objects.equals(name, itemType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
