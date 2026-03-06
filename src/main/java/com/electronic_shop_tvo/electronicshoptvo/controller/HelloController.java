package com.electronic_shop_tvo.electronicshoptvo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {

    @GetMapping
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }
}
