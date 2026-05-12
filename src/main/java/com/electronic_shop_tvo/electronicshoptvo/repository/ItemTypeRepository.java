package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;

import java.util.List;

public interface ItemTypeRepository {

    List<ItemType> getAllItemTypes(boolean isRemoved);

    ItemType getItemTypeById(int id, boolean isRemoved);

    void addItemType(ItemType itemType);

    void updateItemType(int id, ItemType itemType, boolean isRemoved);

    void deleteItemType(int id);
}
