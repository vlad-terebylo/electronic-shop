package com.electronic_shop_tvo.electronicshoptvo.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Purchase {
    private static long counter = 0;
    private long id;
    private String email;
    private String cardNumber;

    public Purchase(String email, String cardNumber) {
        this.id = ++counter;
        this.email = email;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Purchase purchase = (Purchase) object;
        return Objects.equals(email, purchase.email) && Objects.equals(cardNumber, purchase.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, cardNumber);
    }
}
