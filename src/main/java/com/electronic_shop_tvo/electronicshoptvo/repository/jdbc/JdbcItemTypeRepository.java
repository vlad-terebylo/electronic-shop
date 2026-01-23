package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.exception.ItemNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.exception.ItemTypeNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import com.electronic_shop_tvo.electronicshoptvo.repository.ItemTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;

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
    public ItemType getItemTypeByItemId(int id) {
        String sqlGetItemTypeById = """
                SELECT *
                FROM item_type
                WHERE id = %s
                """;

        List<ItemType> itemTypeList = jdbcTemplate.query(sqlGetItemTypeById.formatted(id), ROW_MAPPER);
        if (itemTypeList.isEmpty()) {
            throw new ItemTypeNotFoundException("This list is empty");
        }

        return itemTypeList.get(0);
    }
}
