package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.exception.ItemTypeNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


import java.util.List;

@Service
public class ItemTypeService {

    private ItemTypeRepository itemTypeRepository;

    @Autowired
    public ItemTypeService(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    public List<ItemType> getAllItemTypes() {
        return this.itemTypeRepository.getAllItemTypes();
    }

    public ItemType getItemTypeById(int id) {
        return this.itemTypeRepository.getItemTypeById(id);
    }

    public void addItemType(ItemType itemType) {
        this.itemTypeRepository.addItemType(itemType);
    }

    public void updateItemType(int id, ItemType itemType) {
        ItemType oldItemType = itemTypeRepository.getItemTypeById(id);

        if (isNull(oldItemType)) {
            throw new ItemTypeNotFoundException("Item type is null");
        }

        this.itemTypeRepository.updateItemType(id, itemType);
    }

    public void deleteItemType(int id) {
        this.itemTypeRepository.deleteItemType(id);
    }
}
