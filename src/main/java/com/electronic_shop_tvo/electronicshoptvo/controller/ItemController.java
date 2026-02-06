package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.RequestQuantity;
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
                .map(item -> new ItemDto(
                        item.getTitle(),
                        item.getPrice(),
                        item.getProducingYear(),
                        item.getManufacturer(),
                        item.getQuantity(),
                        item.getItemTypeId()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable int id) {
        log.info("Getting item type by id");

        Item item = this.itemService.getItemById(id);

        return new ItemDto
                (
                        item.getTitle(),
                        item.getPrice(),
                        item.getProducingYear(),
                        item.getManufacturer(),
                        item.getQuantity(),
                        item.getItemTypeId()
                );
    }

    @GetMapping("/getByTitle/{title}")
    public List<ItemDto> getItemsByTitle(@PathVariable String title) {
        return itemService.getItemsByTitle(title).stream()
                .map(item -> new ItemDto(
                        item.getTitle(),
                        item.getPrice(),
                        item.getProducingYear(),
                        item.getManufacturer(),
                        item.getQuantity(),
                        item.getItemTypeId()
                ))
                .toList();
    }

    @PostMapping
    public void addNewItem(@Valid @RequestBody CreateItemDto createItemDto) {
        this.itemService.addNewItem(new Item(createItemDto));
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable int id, @Valid @RequestBody UpdateItemDto updateItemDto) {
        Item item = itemService.getItemById(id);

        item.setTitle(updateItemDto.title());
        item.setPrice(updateItemDto.price());
        item.setQuantity(updateItemDto.quantity());
        item.setItemTypeId(updateItemDto.itemTypeId());

        this.itemService.updateItem(id, item);
    }

    @PatchMapping("/add/{id}")
    public void addQuantity(@PathVariable int id, @Valid @RequestBody RequestQuantity requestQuantity) {
        this.itemService.addQuantity(id, requestQuantity);
    }

    @PatchMapping("/remove/{id}")
    public void removeQuantity(@PathVariable int id, @Valid @RequestBody RequestQuantity requestQuantity) {
        this.itemService.removeQuantity(id, requestQuantity);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        this.itemService.deleteItem(id);
    }

}
