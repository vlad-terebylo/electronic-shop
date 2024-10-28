package com.electronic_shop_tvo.electronicshoptvo.repository.jdbc;

import com.electronic_shop_tvo.electronicshoptvo.exception.UserNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.User;
import com.electronic_shop_tvo.electronicshoptvo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<User> findByUsername(String username) {
        String sqlGetUsername = """
                SELECT *
                FROM "user"
                WHERE login = :login;
                """;

        List<User> users = jdbcTemplate.query(sqlGetUsername, Map.of(
                "login", username
        ), ROW_MAPPER);

        if (users.isEmpty()) {
            throw new UserNotFoundException("The users not found");
        }

        return users;
    }
}
