package com.electronic_shop_tvo.electronicshoptvo.unit;


import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void should_return_all_items() {
        List<Item> expectedList = new ArrayList<>();
        expectedList.add(new Item(1, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2));

        when(itemRepository.getAllItems()).thenReturn(expectedList);

        List<Item> result = itemService.getAllItems();

        assertEquals(expectedList, result);
    }

    @Test
    public void should_return_item_by_id() {
        int id = 1;
        Item expectedItem = new Item(id, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        when(itemRepository.getItemById(id)).thenReturn(expectedItem);

        Item result = itemService.getItemById(id);

        assertEquals(expectedItem, result);
    }

    @Test
    public void should_return_items_by_title() {
        String title = "MacBook Pro";
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(new Item(1, title, BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2));

        when(itemRepository.getItemsByTitle(title)).thenReturn(expectedItems);

        List<Item> result = itemService.getItemsByTitle(title);

        assertEquals(expectedItems, result);
    }

    @Test
    public void should_add_new_item() {
        Item item = new Item(1, "MacBook Pro", BigDecimal.valueOf(1000),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);

        // when
        itemService.addNewItem(item);

        // verify
        verify(itemRepository, times(1)).addNewItem(item);
    }

    @Test
    public void should_update_item_info() {
        Item item = new Item(1, "MacBook Air", BigDecimal.valueOf(800),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                "Apple", 50, 2);
        int id = 1;

        itemService.updateItem(id, item);

        verify(itemRepository, times(1)).updateItem(id, item);
    }

    @Test
    public void should_delete_item() {
        int id = 1;

        itemService.deleteItem(id);

        verify(itemRepository, times(1)).deleteItem(id);
    }
}
