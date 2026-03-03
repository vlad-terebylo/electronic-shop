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

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    @NotNull
    @Positive
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String email;

    @NotBlank
    @Size(min = 16, max = 16)
    private String cardNumber;

    private List<PurchaseItemDto> purchaseItems;

    @NotNull
    @Positive
    private BigDecimal totalPrice;


    public PurchaseDto(Purchase purchase) {
        this.id = purchase.getId();
        this.email = purchase.getEmail();
        this.cardNumber = purchase.getCardNumber();
        this.purchaseItems = purchase.getPurchaseItems().stream()
                .map(PurchaseItemDto::new)
                .toList();

        this.totalPrice = purchase.getTotalPrice();
    }
}
