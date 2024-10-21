package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private int roleId;
}
