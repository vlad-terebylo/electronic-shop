package com.electronic_shop_tvo.electronicshoptvo.integration.config.repository;

import com.electronic_shop_tvo.electronicshoptvo.exception.ItemNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ItemTestRepository {

    private BeanPropertyRowMapper<Item> ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
    private final NamedParameterJdbcOperations jdbcTemplate;

    public List<Item> getAllItems() {
        String sqlSelectAll = """
                SELECT *
                FROM item
                """;

        return jdbcTemplate.query(sqlSelectAll, ROW_MAPPER);
    }

    public Item getItemById(int id) {
        String sqlGetItemById = """
                SELECT *
                FROM item
                WHERE id = %s;
                """;

        List<Item> itemList = jdbcTemplate.query(sqlGetItemById.formatted(id), ROW_MAPPER);
        if (itemList.isEmpty()) {
            throw new ItemNotFoundException("This list is empty");
        }

        return itemList.get(0);
    }


    public List<Item> getItemsByTitle(String title) {
        String sqlGetItemsByTitle = """
                SELECT *
                FROM item
                WHERE title = :title;
                """;

        List<Item> items = jdbcTemplate.query(sqlGetItemsByTitle, Map.of(
                "title", title), ROW_MAPPER);

        if (items.isEmpty()) {
            throw new ItemNotFoundException("This list is empty");
        }


        return items;
    }

    public void addNewItem(Item item) {
        String sqlAddItem = """
                INSERT INTO item(title, price, producing_year, manufacturer, quantity, item_type_id)
                VALUES(:title, :price, :producing_year, :manufacturer, :quantity, :item_type_id);
                """;
        jdbcTemplate.update(sqlAddItem, Map.of(
                "title", item.getTitle(),
                "price", item.getPrice(),
                "producing_year", item.getProducingYear(),
                "manufacturer", item.getManufacturer(),
                "quantity", item.getQuantity(),
                "item_type_id", 2
        ));
    }

    public void updateItem(int id, Item item) {
        String sqlUpdateItem = """
                UPDATE item
                SET title = :title, price = :price, producing_year = :producing_year, manufacturer = :manufacturer, quantity = :quantity, item_type_id = :item_type_id
                WHERE id = :id;
                """;

        jdbcTemplate.update(sqlUpdateItem, Map.of(
                "id", id,
                "title", item.getTitle(),
                "price", item.getPrice(),
                "producing_year", item.getProducingYear(),
                "manufacturer", item.getManufacturer(),
                "quantity", item.getQuantity(),
                "item_type_id", 2
        ));
    }

    public void deleteItem(int id) {
        String sqlDeleteItem = """
                DELETE FROM item
                WHERE id = :id;          
                """;

        jdbcTemplate.update(sqlDeleteItem, Map.of(
                "id", id
        ));
    }

    public void clear() {
        String sqlRestartItem = """
                DELETE 
                FROM item;
                """;

        String sqlRestartCounter = """
                ALTER SEQUENCE item_id_seq RESTART WITH 1;
                """;

        jdbcTemplate.update(sqlRestartItem, Map.of());
        jdbcTemplate.update(sqlRestartCounter, Map.of());
    }
}
