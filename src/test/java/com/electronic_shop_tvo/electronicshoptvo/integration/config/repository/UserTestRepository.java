package com.electronic_shop_tvo.electronicshoptvo.integration.config.repository;

import com.electronic_shop_tvo.electronicshoptvo.exception.UserNotFoundException;
import com.electronic_shop_tvo.electronicshoptvo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserTestRepository {

    private final NamedParameterJdbcOperations jdbcTemplate;
    private BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

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

    public void save(User user) {
        String sqlAddNew = """
                INSERT INTO "user"(name, login, password, role_id)
                VALUES(:name, :login, :password, :role_id);
                """;

        jdbcTemplate.update(sqlAddNew, Map.of(
                "name", user.getName(),
                "login", user.getLogin(),
                "password", user.getPassword(),
                "role_id", user.getRoleId()
        ));
    }

    public void clear() {
        String sqlRestartUser = """
                DELETE 
                FROM "user";
                """;

        String sqlRestartId = """
                ALTER SEQUENCE user_id_seq RESTART WITH 1;
                """;

        jdbcTemplate.update(sqlRestartUser, Map.of());
        jdbcTemplate.update(sqlRestartId, Map.of());
    }
}
