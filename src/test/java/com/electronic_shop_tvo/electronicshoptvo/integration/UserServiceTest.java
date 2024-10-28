package com.electronic_shop_tvo.electronicshoptvo.integration;

import com.electronic_shop_tvo.electronicshoptvo.integration.config.repository.UserTestRepository;
import com.electronic_shop_tvo.electronicshoptvo.model.User;
import com.electronic_shop_tvo.electronicshoptvo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTestRepository testRepository;

    @BeforeEach
    public void cleanUp() {
        testRepository.clear();
    }

    @Test
    void should_return_user_by_username() {
        String login = "vlad_tvo_shop";
        User user = new User(1, "Vlad",
                login,
                "R$_pin/*", 1);

        testRepository.save(user);

        List<User> result = userService.findByUsername(login);
        assertEquals(user, result.get(0));
    }

}
