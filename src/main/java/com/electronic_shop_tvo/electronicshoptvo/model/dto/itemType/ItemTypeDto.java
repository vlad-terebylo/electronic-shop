package com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemTypeDto {
    @NotBlank
    @Size(min = 2, max = 30)
    private String title;

    public ItemTypeDto(ItemType itemType) {
        this.title = itemType.getTitle();
    }

    public ItemType toDomain() {
        return new ItemType(
                this.title
        );
    }
}
