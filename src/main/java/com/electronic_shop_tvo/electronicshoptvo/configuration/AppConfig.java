package com.electronic_shop_tvo.electronicshoptvo.configuration;

import com.electronic_shop_tvo.electronicshoptvo.repository.ItemRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.UserRepository;
import com.electronic_shop_tvo.electronicshoptvo.repository.jdbc.JdbcItemDatabaseRealization;
import com.electronic_shop_tvo.electronicshoptvo.repository.jdbc.JdbcUserDatabaseRealization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
public class AppConfig {

    @Bean
    public ItemRepository getItemRepository(NamedParameterJdbcOperations jdbcTemplate) {
        return new JdbcItemDatabaseRealization(jdbcTemplate);
    }

    @Bean
    public UserRepository getUserRepository(NamedParameterJdbcOperations jdbcTemplate) {
        return new JdbcUserDatabaseRealization(jdbcTemplate);
    }

}
