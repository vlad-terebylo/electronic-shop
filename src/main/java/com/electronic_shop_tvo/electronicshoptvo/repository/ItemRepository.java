package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems(boolean isRemoved);

    Item getItemById(int id, boolean isRemoved);

    List<Item> getItemsByTitle(String title, boolean isRemoved);

    void addNewItem(Item item);

    void updateItem(int id, Item item, boolean isRemoved);

    Integer getQuantity(int id, boolean isRemoved);

    void updateQuantity(int id, Integer newQuantity, boolean isRemoved);

    void deleteItem(int id);
}
