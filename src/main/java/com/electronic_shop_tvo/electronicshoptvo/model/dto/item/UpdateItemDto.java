package com.electronic_shop_tvo.electronicshoptvo.model.dto.item;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemDto {
    @NotBlank @Size(min = 2, max = 30) String title;
    @NotNull @Positive BigDecimal price;
    @NotNull @Positive Integer quantity;
    @NotNull Integer itemTypeId;

    public Item toDomain() {
        return new Item(
                this.title,
                this.price,
                this.quantity,
                this.itemTypeId
        );
    }
}
