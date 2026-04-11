package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.exception.ItemNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcItemRepository implements ItemRepository {

    private static final BeanPropertyRowMapper<Item> ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<Item> getAllItems(boolean isRemoved) {
        String sqlGetAllItems = """
                SELECT *
                FROM item
                WHERE is_removed = :isRemoved;
                """;

        return jdbcTemplate.query(
                sqlGetAllItems,
                Map.of(
                        "is_removed", isRemoved
                ),
                ROW_MAPPER);
    }

    @Override
    public Item getItemById(int id, boolean isRemoved) {
        String sqlGetItemById = """
                SELECT *
                FROM item
                WHERE id = :id AND is_removed = :isRemoved;
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sqlGetItemById,
                    Map.of(
                            "id", id,
                            "isRemoved", isRemoved
                    ),
                    ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Item not found, id=" + id);
        }
    }


    @Override
    public List<Item> getItemsByTitle(String title, boolean isRemoved) {
        String sqlGetItemsByTitle = """
                SELECT *
                FROM item
                WHERE title = :title AND is_removed = :isRemoved;
                """;

        List<Item> items = jdbcTemplate.query(
                sqlGetItemsByTitle,
                Map.of(
                        "title", title,
                        "isRemoved", isRemoved
                ),
                ROW_MAPPER);

        if (items.isEmpty()) {
            throw new ItemNotFoundException("This list is empty");
        }

        return items;
    }

    @Override
    public void addNewItem(Item item) {
        String sqlAddItem = """
                INSERT INTO item(title, price, producing_year, manufacturer, quantity, item_type_id)
                VALUES(:title, :price, :producing_year, :manufacturer, :quantity, :item_type_id)
                """;

        jdbcTemplate.update(
                sqlAddItem,
                Map.of(
                        "title", item.getTitle(),
                        "price", item.getPrice(),
                        "producing_year", item.getProducingYear(),
                        "manufacturer", item.getManufacturer(),
                        "quantity", item.getQuantity(),
                        "item_type_id", item.getItemTypeId()
                ));
    }

    @Override
    public void updateItem(int id, Item item, boolean isRemoved) {
        String sqlUpdateItem = """
                UPDATE item
                SET title = :title, price = :price, producing_year = :producing_year, manufacturer = :manufacturer, quantity = :quantity, item_type_id = :item_type_id
                WHERE id = :id AND is_removed = :isRemoved;
                """;

        jdbcTemplate.update(
                sqlUpdateItem,
                Map.of(
                        "id", id,
                        "title", item.getTitle(),
                        "price", item.getPrice(),
                        "producing_year", item.getProducingYear(),
                        "manufacturer", item.getManufacturer(),
                        "quantity", item.getQuantity(),
                        "item_type_id", item.getItemTypeId(),
                        "isRemoved", isRemoved
                ));
    }

    @Override
    public Integer getQuantity(int id, boolean isRemoved) {
        String sqlGetQuantity = """
                SELECT quantity
                FROM item
                WHERE id = :id AND is_removed = :isRemoved
                """;

        return jdbcTemplate.queryForObject(
                sqlGetQuantity,
                Map.of(
                        "id", id,
                        "isRemoved", isRemoved
                ),
                Integer.class);
    }

    @Override
    public void updateQuantity(int id, Integer newQuantity, boolean isRemoved) {
        String sqlUpdateQuantity = """
                UPDATE item
                SET quantity = :quantity
                WHERE id = :id AND is_removed = :isRemoved
                """;

        jdbcTemplate.update(
                sqlUpdateQuantity,
                Map.of(
                        "id", id,
                        "quantity", newQuantity,
                        "isRemoved", isRemoved
                ));
    }

    @Override
    public void deleteItem(int id) {
        String sqlDeleteItem = """
                UPDATE item
                SET is_removed = true
                WHERE id = :id
                """;

        jdbcTemplate.update(
                sqlDeleteItem,
                Map.of(
                        "id", id
                ));
    }
}
