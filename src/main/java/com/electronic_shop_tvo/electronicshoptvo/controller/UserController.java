package com.electronic_shop_tvo.electronicshoptvo.controller;

import com.electronic_shop_tvo.electronicshoptvo.model.User;
import com.electronic_shop_tvo.electronicshoptvo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public List<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

}
