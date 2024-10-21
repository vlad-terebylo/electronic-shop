package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;


@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private int id;
    private String email;
    private String cardNumber;
    private List<Integer> itemIds;
}
