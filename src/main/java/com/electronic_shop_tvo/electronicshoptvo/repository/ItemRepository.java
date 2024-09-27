package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    Item getItemById(int id);

    List<Item> getItemsByTitle(String title);

    void addNewItem(Item item);

    void updateItem(int id, Item item);

    void deleteItem(int id);
}
