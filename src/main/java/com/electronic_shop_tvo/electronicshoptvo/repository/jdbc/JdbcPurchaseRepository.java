package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.exception.PurchaseNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcPurchaseRepository implements PurchaseRepository {
    private static final BeanPropertyRowMapper<Purchase> ROW_MAPPER = new BeanPropertyRowMapper<>(Purchase.class);
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<Purchase> getAllPurchases() {
        String sqlPurchase = """
                SELECT 
                    p.id            AS id,
                    p.email   AS email,
                    p.card_number          AS card_number,
                    p.total_price          AS total_price,
                    pi.item_id      AS item_id,
                    pi.quantity     AS quantity
                FROM customer_purchase p
                LEFT JOIN purchase_item pi 
                       ON p.id = pi.purchase_id
                ORDER BY p.id
                """;

        return jdbcTemplate.query(sqlPurchase, rs -> {

            Map<Integer, Purchase> purchaseMap = new LinkedHashMap<>();

            while (rs.next()) {

                Integer purchaseId = rs.getInt("id");

                Purchase purchase = purchaseMap.get(purchaseId);

                if (purchase == null) {
                    purchase = new Purchase();
                    purchase.setId(purchaseId);
                    purchase.setEmail(rs.getString("email"));
                    purchase.setCardNumber(rs.getString("card_number"));
                    purchase.setTotalPrice(rs.getBigDecimal("total_price"));
                    purchase.setPurchaseItems(new ArrayList<>());

                    purchaseMap.put(purchaseId, purchase);
                }

                Integer itemId = rs.getObject("item_id", Integer.class);

                if (itemId != null) {
                    PurchaseItem item = new PurchaseItem();
                    item.setItemId(itemId);
                    item.setQuantity(rs.getInt("quantity"));

                    purchase.getPurchaseItems().add(item);
                }
            }

            return new ArrayList<>(purchaseMap.values());
        });
    }

    @Override
    public Purchase getPurchaseById(int id) {

        String sql = """
                SELECT 
                    p.id            AS id,
                    p.email         AS email,
                    p.card_number   AS card_number,
                    p.total_price   AS total_price,
                    pi.item_id      AS item_id,
                    pi.quantity     AS quantity
                FROM customer_purchase p
                LEFT JOIN purchase_item pi 
                       ON p.id = pi.purchase_id
                WHERE p.id = :id
                """;

        return jdbcTemplate.query(sql, Map.of("id", id), rs -> {

            Purchase purchase = null;

            while (rs.next()) {

                if (purchase == null) {
                    purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setEmail(rs.getString("email"));
                    purchase.setCardNumber(rs.getString("card_number"));
                    purchase.setTotalPrice(rs.getBigDecimal("total_price"));
                    purchase.setPurchaseItems(new ArrayList<>());
                }

                Integer itemId = rs.getObject("item_id", Integer.class);
                if (itemId != null) {
                    PurchaseItem item = new PurchaseItem();
                    item.setItemId(itemId);
                    item.setQuantity(rs.getInt("quantity"));
                    purchase.getPurchaseItems().add(item);
                }
            }

            if (purchase == null) {
                throw new PurchaseNotFoundException("No purchase found by id " + id);
            }

            return purchase;
        });
    }

    @Override
    public void save(Purchase purchase) {
        String sqlAddCustomerInfo = """
                INSERT INTO customer_purchase(email, card_number, total_price)
                VALUES(:email, :cardNumber, :totalPrice)
                RETURNING id
                """;

        String sqlAddPurchaseItem = """
                INSERT INTO purchase_item(purchase_id, item_id, quantity)
                VALUES(:purchase_id, :item_id, :quantity)
                """;

        int id = jdbcTemplate.queryForObject(sqlAddCustomerInfo, Map.of(
                "email", purchase.getEmail(),
                "cardNumber", purchase.getCardNumber(),
                "totalPrice", purchase.getTotalPrice()
        ), Integer.class);

        List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();

        for (PurchaseItem purchaseItem : purchaseItems) {
            jdbcTemplate.update(sqlAddPurchaseItem, Map.of(
                    "purchase_id", id,
                    "item_id", purchaseItem.getItemId(),
                    "quantity", purchaseItem.getQuantity()
            ));
        }
    }
}
