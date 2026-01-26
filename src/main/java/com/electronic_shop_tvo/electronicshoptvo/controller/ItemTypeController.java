package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itemTypes")
@Slf4j
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @GetMapping
    public List<ItemType> getAllItemTypes() {
        log.info("Getting all item types");
        return this.itemTypeService.getAllItemTypes();
    }

    @GetMapping("/{id}")
    public ItemType getItemTypeById(@PathVariable int id) {
        log.info("Getting item type by id");
        return this.itemTypeService.getItemTypeByItemId(id);
    }

    @PostMapping
    public void addItemType(@RequestBody ItemType itemType) {
        this.itemTypeService.addItemType(itemType);
    }

    @PatchMapping("/{id}")
    public void updateItemType(@PathVariable int id, @RequestBody ItemType itemType) {
        this.itemTypeService.updateItemType(id, itemType);
    }

    @DeleteMapping("/{id}")
    public void deleteItemType(@PathVariable int id) {
        this.itemTypeService.deleteItemType(id);
    }
}
