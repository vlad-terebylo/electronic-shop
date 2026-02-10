package com.electronic_shop_tvo.electronicshoptvo.model.dto.item;

import com.electronic_shop_tvo.electronicshoptvo.model.Item;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ItemDto {
    @NotBlank
    @Size(min = 2, max = 30)
    private String title;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Past
    private LocalDateTime producingYear;

    @NotBlank
    @Size(min = 2, max = 30)
    private String manufacturer;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Integer itemTypeId;

    public ItemDto(Item item) {
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.producingYear = item.getProducingYear();
        this.manufacturer = item.getManufacturer();
        this.quantity = item.getQuantity();
        this.itemTypeId = item.getItemTypeId();
    }
}
