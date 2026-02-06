package com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateItemTypeDto
        (
                @NotBlank @Size(min = 2, max = 30) String title
        ) {
}
