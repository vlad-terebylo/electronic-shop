package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.exception.QuantityIsNotValidException;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.RequestQuantity;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return this.itemRepository.getAllItems();
    }

    public Item getItemById(int id) {
        return this.itemRepository.getItemById(id);
    }

    public List<Item> getItemsByTitle(String title) {
        return this.itemRepository.getItemsByTitle(title);
    }

    public void addNewItem(Item item) {
        this.itemRepository.addNewItem(item);
    }

    public void updateItem(int id, Item item) {
        this.itemRepository.updateItem(id, item);
    }

    public void addQuantity(int id, RequestQuantity requestQuantity) {
        if (!isValid(requestQuantity.quantity())) {
            throw new QuantityIsNotValidException("The quantity must be more than zero!");
        }

        this.itemRepository.addQuantity(id, requestQuantity);
    }

    public void removeQuantity(int id, RequestQuantity requestQuantity) {
        if (!isValid(requestQuantity.quantity())) {
            throw new QuantityIsNotValidException("The quantity must be more than zero!");
        }

        this.itemRepository.removeQuantity(id, requestQuantity);
    }

    private boolean isValid(int quantity) {
        return quantity > 0;
    }

    public void deleteItem(int id) {
        this.itemRepository.deleteItem(id);
    }
}
