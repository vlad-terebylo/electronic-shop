package com.electronic_shop_tvo.electronicshoptvo.integration.config;

import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.ItemTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.PurchaseTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.UserTestRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@TestConfiguration
public class TestPostgresConfig {

    @Bean
    public ItemTestRepository getItemRepo(NamedParameterJdbcOperations jdbcTemplate) {
        return new ItemTestRepository(jdbcTemplate);
    }

    @Bean
    public PurchaseTestRepository getPurchaseRepo(NamedParameterJdbcOperations jdbcTemplate) {
        return new PurchaseTestRepository(jdbcTemplate);
    }

    @Bean
    public UserTestRepository getUserRepo(NamedParameterJdbcOperations jdbcTemplate) {
        return new UserTestRepository(jdbcTemplate);
    }
}
