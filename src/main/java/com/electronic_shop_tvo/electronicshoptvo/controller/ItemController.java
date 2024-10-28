package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.RequestQuantity;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return this.itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id) {
        return this.itemService.getItemById(id);
    }

    @GetMapping("getByTitle/{title}")
    public List<Item> getItemsByTitle(@PathVariable String title) {
        return this.itemService.getItemsByTitle(title);
    }

    @PostMapping
    public void addNewItem(@RequestBody Item item) {
        this.itemService.addNewItem(item);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable int id, @RequestBody Item item) {
        this.itemService.updateItem(id, item);
    }

    @PatchMapping("/add/{id}")
    public void addQuantity(@PathVariable int id, @RequestBody RequestQuantity requestQuantity) {
        this.itemService.addQuantity(id, requestQuantity);
    }

    @PatchMapping("/remove/{id}")
    public void removeQuantity(@PathVariable int id, @RequestBody RequestQuantity requestQuantity) {
        this.itemService.removeQuantity(id, requestQuantity);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        this.itemService.deleteItem(id);
    }

}
