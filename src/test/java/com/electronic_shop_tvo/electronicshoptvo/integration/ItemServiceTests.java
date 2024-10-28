package com.electronic_shop_tvo.electronicshoptvo.integration;

import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.ItemTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.PurchaseTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ItemServiceTests extends BaseTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemTestRepository itemRepository;

    @Autowired
    private PurchaseTestRepository purchaseRepository;


    @BeforeEach
    public void cleanUp() {
        purchaseRepository.clear();
        itemRepository.clear();
    }

    @Test
    void should_return_all_items() {
        List<Item> result = itemService.getAllItems();
        assertEquals(itemRepository.getAllItems(), result);
    }

    @Test
    void should_return_item_by_id() {
        int id = 1;

        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        itemService.addNewItem(item);
        Item result = itemService.getItemById(id);

        assertEquals(itemRepository.getItemById(id), result);
    }

    @Test
    void should_return_items_by_title() {
        String title = "MacBook Pro";

        Item item = new Item(1, title, BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        itemService.addNewItem(item);

        List<Item> result = itemService.getItemsByTitle(title);

        assertEquals(itemRepository.getItemsByTitle(title), result);
    }

    @Test
    void should_add_new_item() {
        Item item = new Item(1, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        itemService.addNewItem(item);

        assertTrue(itemRepository.getAllItems().contains(item));
    }

    @Test
    void should_update_item() {
        int id = 1;
        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        itemService.addNewItem(item);

        BigDecimal newPrice = BigDecimal.valueOf(1500);
        int newQuantity = 70;
        item.setPrice(newPrice);
        item.setQuantity(newQuantity);

        itemService.updateItem(id, item);

        Item gotItem = itemService.getItemById(id);

        assertEquals(item.getPrice(), gotItem.getPrice());
        assertEquals(item.getQuantity(), gotItem.getQuantity());
    }

    @Test
    void should_delete_item() {
        int id = 1;
        Item item = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        itemService.addNewItem(item);
        itemService.deleteItem(id);

        assertFalse(itemRepository.getAllItems().contains(item));
    }

}
