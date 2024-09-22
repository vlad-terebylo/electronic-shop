package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tvo")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService service) {
        this.itemService = service;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return this.itemService.getAllItems();
    }

    @GetMapping("{/id}")
    public Item getItemById(@PathVariable long id) {
        return this.itemService.getItemById(id);
    }

    @GetMapping("/{title}")
    public List<Item> getItemsByTitle(@PathVariable String title) {
        return this.itemService.getItemsByTitle(title);
    }

    @PostMapping
    public void addNewItem() {
        this.itemService.addNewItem();
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable long id, @RequestBody Item item) {
        this.itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable long id) {
        this.itemService.deleteItem(id);
    }

}
