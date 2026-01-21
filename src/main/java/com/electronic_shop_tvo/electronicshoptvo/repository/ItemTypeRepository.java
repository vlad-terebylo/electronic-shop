package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;

import java.util.List;

public interface ItemTypeRepository {

    List<ItemType> getAllItemTypes();

    ItemType getItemTypeByItemId(int id);
}
