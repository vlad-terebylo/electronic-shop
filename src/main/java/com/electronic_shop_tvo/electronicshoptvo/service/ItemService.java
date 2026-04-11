package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.exception.QuantityIsNotValidException;
import com.electronic_shop_tvo.electronicshoptvo.exception.QuantityIsUnderZeroException;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return this.itemRepository.getAllItems(false);
    }

    public Item getItemById(int id) {
        return this.itemRepository.getItemById(id, false);
    }

    public List<Item> getItemsByTitle(String title) {
        return this.itemRepository.getItemsByTitle(title, false);
    }

    public void addNewItem(Item item) {
        this.itemRepository.addNewItem(item);
    }

    public void updateItem(int id, Item item) {
        Item oldItem = this.itemRepository.getItemById(id, false);

        if (isNull(oldItem)) {
            throw new NullPointerException("This item does not exist");
        }

        item.setManufacturer(oldItem.getManufacturer());
        item.setProducingYear(oldItem.getProducingYear());

        this.itemRepository.updateItem(id, item, false);
    }

    public void addQuantity(int id, Integer quantity) {
        if (isNull(quantity)) {
            throw new QuantityIsNotValidException("The quantity must not be null!");
        }

        if (!isPositive(quantity)) {
            throw new QuantityIsUnderZeroException("The quantity must be more than zero!");
        }

        Integer currentQuantity = this.itemRepository.getQuantity(id, false);
        currentQuantity += quantity;

        this.itemRepository.updateQuantity(id, currentQuantity, false);
    }

    public void removeQuantity(int id, Integer quantity) {
        if (isNull(quantity)) {
            throw new QuantityIsNotValidException("The quantity must not be null!");
        }

        if (!isPositive(quantity)) {
            throw new QuantityIsUnderZeroException("The quantity must be more than zero!");
        }

        Integer currentQuantity = this.itemRepository.getQuantity(id, false);
        if (!isPositive(currentQuantity)) {
            throw new QuantityIsUnderZeroException("The quantity is under zero");
        }

        currentQuantity -= quantity;

        this.itemRepository.updateQuantity(id, currentQuantity, false);
    }

    private boolean isPositive(int quantity) {
        return quantity > 0;
    }

    public void deleteItem(int id) {
        this.itemRepository.deleteItem(id);
    }
}
