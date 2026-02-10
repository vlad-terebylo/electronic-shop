package com.electronic_shop_tvo.electronicshoptvo.model.dto.itemType;

import com.electronic_shop_tvo.electronicshoptvo.model.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemTypeDto {
    @NotBlank
    @Size(min = 2, max = 30)
    private String title;

    public UpdateItemTypeDto(ItemType itemType) {
        this.title = itemType.getTitle();
    }

    public ItemType toDomain() {
        return new ItemType(
                this.title
        );
    }
}
