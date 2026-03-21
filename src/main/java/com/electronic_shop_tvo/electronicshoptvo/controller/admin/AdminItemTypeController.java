package com.electronic_shop_tvo.electronicshoptvo.controller.admin;

import com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType.CreateItemTypeDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType.UpdateItemTypeDto;
import com.electronic_shop_tvo.electronicshoptvo.service.ItemTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/itemTypes")
public class AdminItemTypeController {
    private final ItemTypeService itemTypeService;

    @PostMapping
    public void addItemType(@Valid @RequestBody CreateItemTypeDto createItemTypeDto) {
        log.info("Adding new item type");

        this.itemTypeService.addItemType(createItemTypeDto.toDomain());
    }

    @PatchMapping("/{id}")
    public void updateItemType(@PathVariable int id, @Valid @RequestBody UpdateItemTypeDto updateItemTypeDto) {
        log.info("Updating an item type with id: {}", id);

        this.itemTypeService.updateItemType(id, updateItemTypeDto.toDomain());
    }

    @DeleteMapping("/{id}")
    public void deleteItemType(@PathVariable int id) {
        log.info("Deleting item with id: {}", id);

        this.itemTypeService.deleteItemType(id);
    }
}
