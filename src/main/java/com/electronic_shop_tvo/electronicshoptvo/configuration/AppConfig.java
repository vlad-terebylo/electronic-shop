package com.electronic_shop_tvo.electronicshoptvo.configuration;

import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.PurchaseRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.UserRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.jdbc.JdbcItemRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.jdbc.JdbcPurchaseRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.jdbc.JdbcUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
public class AppConfig {

    @Bean
    public ItemRepository getItemRepository(NamedParameterJdbcOperations jdbcTemplate) {
        return new JdbcItemRepository(jdbcTemplate);
    }

    @Bean
    public UserRepository getUserRepository(NamedParameterJdbcOperations jdbcTemplate) {
        return new JdbcUserRepository(jdbcTemplate);
    }

    @Bean
    public PurchaseRepository getPurchaseRepository(NamedParameterJdbcOperations jdbcTemplate){
        return new JdbcPurchaseRepository(jdbcTemplate);
    }


}
