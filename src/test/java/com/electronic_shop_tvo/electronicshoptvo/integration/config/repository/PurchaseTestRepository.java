package com.electronic_shop_tvo.electronicshoptvo.integration.config.repository;

import com.electronic_shop_tvo.electronicshoptvo.exception.PurchaseNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PurchaseTestRepository {

    private static final BeanPropertyRowMapper<Purchase> ROW_MAPPER = new BeanPropertyRowMapper<>(Purchase.class);
    private final NamedParameterJdbcOperations jdbcTemplate;


    public List<Purchase> getAllPurchases() {
        String sqlGetAllPurchases = """
                SELECT *
                FROM customer_purchase
                """;

        String sqlGetPurchaseIds = """
                SELECT item_id
                FROM purchase_item
                WHERE purchase_id = :id
                """;


        List<Purchase> purchases = jdbcTemplate.query(sqlGetAllPurchases, ROW_MAPPER);

        return setItemIds(purchases, sqlGetPurchaseIds);
    }

    public Purchase getPurchaseById(int id) {
        String sqlGetPurchaseById = """
                SELECT *
                FROM customer_purchase
                WHERE id = :id
                """;

        String sqlGetPurchaseIds = """
                SELECT item_id
                FROM purchase_item
                WHERE purchase_id = :id
                """;

        List<Purchase> purchases = jdbcTemplate.query(sqlGetPurchaseById, Map.of(
                "id", id
        ), ROW_MAPPER);

        List<Integer> itemIds = jdbcTemplate.queryForList(sqlGetPurchaseIds, Map.of(
                "id", id
        ), Integer.class);

        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("No purchase found by id " + id);
        }

        return purchases.get(0).withItemIds(itemIds);
    }

    public List<Purchase> getPurchasesByCard(String cardNumber) {
        String sqlGetPurchasesByCard = """
                SELECT *
                FROM customer_purchase
                WHERE card_number = :card_number
                """;

        String sqlGetPurchaseIds = """
                SELECT item_id
                FROM purchase_item
                WHERE purchase_id = :id
                """;

        List<Purchase> purchases = jdbcTemplate.query(sqlGetPurchasesByCard, Map.of(
                "card_number", cardNumber
        ), ROW_MAPPER);

        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("The purchases not found");
        }

        return setItemIds(purchases, sqlGetPurchaseIds);
    }

    public List<Purchase> getPurchasesByEmail(String email) {
        String sqlGetPurchasesByEmail = """
                SELECT *
                FROM customer_purchase
                WHERE email = :email
                """;

        String sqlGetItemIds = """
                SELECT item_id
                FROM purchase_item
                WHERE purchase_id = :id
                """;

        List<Purchase> purchases = jdbcTemplate.query(sqlGetPurchasesByEmail, Map.of(
                "email", email
        ), ROW_MAPPER);

        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("The purchases not found");
        }

        return setItemIds(purchases, sqlGetItemIds);
    }

    private List<Purchase> setItemIds(List<Purchase> purchases, String sqlQuery) {
        for (Purchase purchase : purchases) {
            int id = purchase.getId();

            List<Integer> itemIds = jdbcTemplate.queryForList(sqlQuery, Map.of(
                    "id", id
            ), Integer.class);

            purchase.setItemIds(itemIds);
        }

        return purchases;
    }

    public void save(Purchase purchase) {
        String sqlAddCustomerInfo = """
                INSERT INTO customer_purchase(email, card_number)
                VALUES(:email, :cardNumber)
                RETURNING id
                """;

        String sqlAddPurchaseItem = """
                INSERT INTO purchase_item(purchase_id, item_id)
                VALUES(:purchase_id, :item_id)
                """;

        int id = jdbcTemplate.queryForObject(sqlAddCustomerInfo, Map.of(
                "email", purchase.getEmail(),
                "cardNumber", purchase.getCardNumber()
        ), Integer.class);

        List<Integer> itemIds = purchase.getItemIds();

        for (Integer itemId : itemIds) {
            jdbcTemplate.update(sqlAddPurchaseItem, Map.of(
                    "purchase_id", id,
                    "item_id", itemId
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
                ALTER SEQUENCE customer_purchase_id_seq RESTART WITH 1;
                """;

        String sqlRestartPurchaseItemId = """
                ALTER SEQUENCE purchase_item_id_seq RESTART WITH 1;
                """;


        jdbcTemplate.update(sqlRestartPurchaseItem, Map.of());
        jdbcTemplate.update(sqlRestartPurchaseItemId, Map.of());
        jdbcTemplate.update(sqlRestartCustomerPurchase, Map.of());
        jdbcTemplate.update(sqlRestartCustomerPurchaseId, Map.of());
    }
}
