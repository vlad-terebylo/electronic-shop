package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.exception.ItemNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.exception.ItemTypeNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcItemTypeRepository implements ItemTypeRepository {
    private static final BeanPropertyRowMapper<ItemType> ROW_MAPPER = new BeanPropertyRowMapper<>(ItemType.class);
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<ItemType> getAllItemTypes() {
        String sqlGetAllItemTypes = """
                SELECT *
                FROM item_type;
                """;

        return jdbcTemplate.query(sqlGetAllItemTypes, ROW_MAPPER);
    }

    @Override
    public ItemType getItemTypeById(int id) {
        String sqlGetItemTypeById = """
                SELECT *
                FROM item_type
                WHERE id = :id
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sqlGetItemTypeById,
                    Map.of("id", id),
                    ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Item type not found, id=" + id);
        }
    }

    @Override
    public void addItemType(ItemType itemType) {
        String sqlAddItemType = """
                INSERT INTO item_type(title)
                VALUES(:title);            
                """;
        jdbcTemplate.update(sqlAddItemType, Map.of(
                "title", itemType.getTitle()
        ));
    }

    @Override
    public void updateItemType(int id, ItemType itemType) {
        String sqlUpdateItem = """
                UPDATE item_type
                SET title = :title
                WHERE id = :id;
                """;

        jdbcTemplate.update(sqlUpdateItem, Map.of(
                "id", id,
                "title", itemType.getTitle()
        ));
    }

    @Override
    public void deleteItemType(int id) {
        String sqlDeleteItem = """
                DELETE FROM item_type
                WHERE id = :id
                """;

        jdbcTemplate.update(sqlDeleteItem, Map.of(
                "id", id
        ));
    }
}
