package com.electronic_shop_tvo.electronicshoptvo.exception;

public class QuantityIsUnderZeroException extends RuntimeException {
    public QuantityIsUnderZeroException(String message) {
        super(message);
    }
}
