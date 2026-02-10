package com.electronic_shop_tvo.electronicshoptvo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RequestQuantityDto(
        @NotNull @Positive Integer quantity
) {
}
