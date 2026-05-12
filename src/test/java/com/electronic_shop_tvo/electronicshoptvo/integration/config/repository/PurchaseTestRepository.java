package com.electronic_shop_tvo.electronicshoptvo.integration.config.repository;

import com.electronic_shop_tvo.electronicshoptvo.exception.PurchaseNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PurchaseTestRepository {

    private static final BeanPropertyRowMapper<Purchase> ROW_MAPPER = new BeanPropertyRowMapper<>(Purchase.class);
    private final NamedParameterJdbcOperations jdbcTemplate;


    public List<Purchase> getAllPurchases() {
        String sqlPurchases = """
                SELECT *
                FROM customer_purchase
                """;

        String sqlPurchaseItems = """
                SELECT item_id, quantity
                FROM purchase_item
                WHERE purchase_id = :id
                """;

        List<Purchase> purchases = jdbcTemplate.query(sqlPurchases, ROW_MAPPER);

        for (Purchase purchase : purchases) {

            List<PurchaseItem> items = jdbcTemplate.query(
                    sqlPurchaseItems,
                    Map.of("id", purchase.getId()),
                    (rs, rowNum) -> {

                        PurchaseItem item = new PurchaseItem();
                        item.setItemId(rs.getInt("item_id"));
                        item.setQuantity(rs.getInt("quantity"));

                        return item;
                    }
            );

            purchase.setPurchaseItems(items);
        }

        return purchases;
    }

    public Purchase getPurchaseById(int id) {

        String sqlGetPurchase = """
                SELECT *
                FROM customer_purchase
                WHERE id = :id
                """;

        String sqlGetPurchaseItems = """
                SELECT item_id, quantity
                FROM purchase_item
                WHERE purchase_id = :id
                """;

        List<Purchase> purchases = jdbcTemplate.query(
                sqlGetPurchase,
                Map.of("id", id),
                ROW_MAPPER
        );

        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("No purchase found by id " + id);
        }

        Purchase purchase = purchases.get(0);

        List<PurchaseItem> items = jdbcTemplate.query(
                sqlGetPurchaseItems,
                Map.of("id", id),
                (rs, rowNum) -> {

                    PurchaseItem item = new PurchaseItem();
                    item.setItemId(rs.getInt("item_id"));
                    item.setQuantity(rs.getInt("quantity"));

                    return item;
                }
        );

        purchase.setPurchaseItems(items);

        return purchase;
    }

    public void save(Purchase purchase) {
        String sqlAddCustomerInfo = """
                INSERT INTO customer_purchase(email, card_number, total_price)
                VALUES(:email, :cardNumber, :totalPrice)
                """;

        String sqlAddPurchaseItem = """
                INSERT INTO purchase_item(purchase_id, item_id, quantity)
                VALUES(:purchase_id, :item_id, :quantity)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource customerParams = new MapSqlParameterSource()
                .addValue("email", purchase.getEmail())
                .addValue("cardNumber", purchase.getCardNumber())
                .addValue("totalPrice", purchase.getTotalPrice());

        jdbcTemplate.update(sqlAddCustomerInfo, customerParams, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId == null) {
            throw new RuntimeException("Failed to retrieve generated ID for purchase");
        }
        int id = generatedId.intValue();

        List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();

        for (PurchaseItem purchaseItem : purchaseItems) {
            jdbcTemplate.update(sqlAddPurchaseItem, Map.of(
                    "purchase_id", id,
                    "item_id", purchaseItem.getItemId(),
                    "quantity", purchaseItem.getQuantity()
            ));
        }
    }

    public void clear() {
        String sqlRestartCustomerPurchase = """
                DELETE 
                FROM customer_purchase;
                """;

        String sqlRestartPurchaseItem = """
                DELETE
                FROM purchase_item;
                """;

        String sqlRestartCustomerPurchaseId = """
                ALTER TABLE customer_purchase AUTO_INCREMENT = 1;
                """;

        String sqlRestartPurchaseItemId = """
                ALTER TABLE purchase_item AUTO_INCREMENT = 1;
                """;


        jdbcTemplate.update(sqlRestartPurchaseItem, Map.of());
        jdbcTemplate.update(sqlRestartPurchaseItemId, Map.of());
        jdbcTemplate.update(sqlRestartCustomerPurchase, Map.of());
        jdbcTemplate.update(sqlRestartCustomerPurchaseId, Map.of());
    }
}
