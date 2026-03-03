package com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTypeDto {

    @NotNull
    @Positive
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String title;

    public ItemTypeDto(ItemType itemType) {
        this.id = itemType.getId();
        this.title = itemType.getTitle();
    }
}
