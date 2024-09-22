package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private static long counter = 0;
    private long id;
    private String name;
    private String login;
    private String password;
    private long roleId;

    public User(String name, String login, String password, long roleId) {
        this.id = ++counter;
        this.name = name;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return roleId == user.roleId && Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, roleId);
    }
}
