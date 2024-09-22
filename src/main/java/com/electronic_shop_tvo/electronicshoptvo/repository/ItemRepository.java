package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    Item getItemById(long id);

    List<Item> getItemsByTitle(String title);

    void addNewItem();

    void updateItem(long id, Item item);

    void deleteItem(long id);
}
