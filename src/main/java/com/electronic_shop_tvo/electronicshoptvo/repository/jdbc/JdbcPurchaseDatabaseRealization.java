package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;

@RequiredArgsConstructor
public class JdbcPurchaseDatabaseRealization implements PurchaseRepository {
    private static final BeanPropertyRowMapper<Purchase> ROW_MAPPER = new BeanPropertyRowMapper<>();
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<Purchase> getAllPurchases() {
        return null;
    }

    @Override
    public Purchase getPurchaseById(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByCard(String cardNumber) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByEmail(String email) {
        return null;
    }

    @Override
    public void save(Purchase purchase) {

    }
}
