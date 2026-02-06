package com.electronic_shop_tvo.electronicshoptvo.model.dto.item;

import jakarta.validation.constraints.*;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateItemDto
        (
                @NotBlank @Size(min = 2, max = 30) String title,
                @NotNull @Positive BigDecimal price,
                @NotNull @Past LocalDateTime producingYear,
                @NotBlank @Size(min = 2, max = 30) String manufacturer,
                @NotNull @Positive Integer quantity,
                @NotNull Integer itemTypeId
        ) {
}
