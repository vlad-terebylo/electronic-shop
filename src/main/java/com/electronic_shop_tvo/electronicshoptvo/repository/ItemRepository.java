package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.RequestQuantityDto;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    Item getItemById(int id);

    List<Item> getItemsByTitle(String title);

    void addNewItem(Item item);

    void updateItem(int id, Item item);

    Integer getQuantity(int id, Integer quantity);

    void updateQuantity(int id, Integer newQuantity);

    void deleteItem(int id);
}
