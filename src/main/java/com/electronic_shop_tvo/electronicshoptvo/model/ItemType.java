package com.electronic_shop_tvo.electronicshoptvo.model;

import com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType.CreateItemTypeDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType.ItemTypeDto;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType.UpdateItemTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemType {
    private int id;
    private String title;

    public ItemType(ItemTypeDto itemTypeDto) {
        this.title = itemTypeDto.title();
    }

    public ItemType(CreateItemTypeDto createItemTypeDto) {
        this.title = createItemTypeDto.title();
    }

    public ItemType(UpdateItemTypeDto updateItemTypeDto) {
        this.title = updateItemTypeDto.title();
    }
}
