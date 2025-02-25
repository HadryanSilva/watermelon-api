package br.com.hadryan.watermelon.purchase.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PurchaseRequest {

    @NotNull
    private List<PurchaseItemRequest> items;

    @NotBlank
    private BigDecimal totalValue;

}
