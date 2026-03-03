package com.electronic_shop_tvo.electronicshoptvo.model.dto.purchase;

import com.electronic_shop_tvo.electronicshoptvo.model.Purchase;
import com.electronic_shop_tvo.electronicshoptvo.model.PurchaseItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePurchaseDto {

    @NotBlank
    @Size(min = 2)
    private String email;

    @NotBlank
    @Size(min = 16, max = 16)
    private String cardNumber;

    private List<PurchaseItemDto> purchaseItems;

    public Purchase toDomain() {
        List<PurchaseItem> items = this.purchaseItems.stream()
                .map(PurchaseItemDto::toDomain)
                .toList();

        return new Purchase(
                this.email,
                this.cardNumber,
                items
        );
    }
}
