package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.RequestQuantityDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.CreateItemDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.ItemDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.item.UpdateItemDto;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems() {
        log.info("Getting all items");

        return itemService.getAllItems().stream()
                .map(ItemDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable int id) {
        log.info("Getting item by id: {}", id);

        return new ItemDto(this.itemService.getItemById(id));
    }

    @GetMapping("/getByTitle/{title}")
    public List<ItemDto> getItemsByTitle(@PathVariable String title) {
        log.info("Getting item by title: {}", title);

        return itemService.getItemsByTitle(title).stream()
                .map(ItemDto::new)
                .toList();
    }

    @PostMapping
    public void addNewItem(@Valid @RequestBody CreateItemDto createItemDto) {
        log.info("Adding new item");

        this.itemService.addNewItem(createItemDto.toDomain());
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable int id, @Valid @RequestBody UpdateItemDto updateItemDto) {
        log.info("Updating an item with id: {}", id);

        this.itemService.updateItem(id, updateItemDto.toDomain());
    }

    @PatchMapping("/add/{id}")
    public void addQuantity(@PathVariable int id, @Valid @RequestBody RequestQuantityDto requestQuantityDto) {
        log.info("Adding extra {} items", requestQuantityDto.quantity());

        this.itemService.addQuantity(id, requestQuantityDto.quantity());
    }

    @PatchMapping("/remove/{id}")
    public void removeQuantity(@PathVariable int id, @Valid @RequestBody RequestQuantityDto requestQuantityDto) {
        log.info("Removing {} items", requestQuantityDto.quantity());

        this.itemService.removeQuantity(id, requestQuantityDto.quantity());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        log.info("Deleting item with id: {}", id);

        this.itemService.deleteItem(id);
    }

}
