package br.com.hadryan.watermelon.purchase.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItemRequest {

    @Positive
    private Integer quantity;

    @NotBlank
    private BigDecimal value;

    @NotNull
    private Long productId;

}
