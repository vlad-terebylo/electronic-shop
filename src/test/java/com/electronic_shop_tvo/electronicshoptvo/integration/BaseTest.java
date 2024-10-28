package com.electronic_shop_tvo.electronicshoptvo.integration;

import com.electronic_shop_tvo.electronicshoptvo.integration.config.TestPostgresConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestPostgresConfig.class)
abstract class BaseTest {

    private static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer("postgres:13")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    static {
        postgreSQL.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        String host = postgreSQL.getHost();
        int port = postgreSQL.getFirstMappedPort();
        registry.add("spring.datasource.url", postgreSQL::getJdbcUrl);
        System.out.println(postgreSQL.getJdbcUrl());
    }

}
