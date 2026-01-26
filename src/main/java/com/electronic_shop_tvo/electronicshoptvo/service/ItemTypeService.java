package com.electronic_shop_tvo.electronicshoptvo.service;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ItemType getItemTypeByItemId(int id) {
        return this.itemTypeRepository.getItemTypeByItemId(id);
    }

    public void addItemType(ItemType itemType) {
        this.itemTypeRepository.addItemType(itemType);
    }

    public void updateItemType(int id, ItemType itemType) {
        this.itemTypeRepository.updateItemType(id, itemType);
    }

    public void deleteItemType(int id) {
        this.itemTypeRepository.deleteItemType(id);
    }
}
