package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int id;
    private String title;
    private BigDecimal price;
    private String producingYear;
    private String manufacturer;
    private int quantity;
    private ItemType itemType;

}
