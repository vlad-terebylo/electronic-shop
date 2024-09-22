package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcItemDatabaseRealization implements ItemRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public Item getItemById(long id) {
        return null;
    }

    @Override
    public List<Item> getItemsByTitle(String title) {
        return null;
    }

    @Override
    public void addNewItem() {

    }

    @Override
    public void updateItem(long id, Item item) {

    }

    @Override
    public void deleteItem(long id) {

    }
}
