package com.electronic_shop_tvo.electronicshoptvo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        String name = authentication != null
                ? authentication.getName()
                : "anonymous";

        return "Hello, " + name;
    }

    @GetMapping("/authenticated/hello")
    public String authenticatedHello(Authentication authentication) {
        return "Hello authenticated, " + authentication.getName();
    }

    @GetMapping("/admin/hello")
    public String adminHello(Authentication authentication) {
        return "Hello admin, " + authentication.getName();
    }
}