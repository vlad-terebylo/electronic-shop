package com.electronic_shop_tvo.electronicshoptvo.repository;

import com.electronic_shop_tvo.electronicshoptvo.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findByUsername(String username);
}
