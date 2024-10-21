package com.electronic_shop_tvo.electronicshoptvo.unit;

import com.electronic_shop_tvo.electronicshoptvo.model.User;
import com.electronic_shop_tvo.electronicshoptvo.repository.UserRepository;
import com.electronic_shop_tvo.electronicshoptvo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void should_return_users_by_username() {
        String username = "vlad_tvo_shop";
        List<User> expectedUsers = new ArrayList<>();

        expectedUsers.add(new User(1, "Vlad",
                username,
                "R$_pin/*", 1));

        when(userRepository.findByUsername(username)).thenReturn(expectedUsers);

        List<User> result = userService.findByUsername(username);

        assertEquals(expectedUsers, result);
    }

}
