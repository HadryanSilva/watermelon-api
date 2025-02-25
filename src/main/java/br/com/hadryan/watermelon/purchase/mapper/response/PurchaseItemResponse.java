package br.com.hadryan.watermelon.purchase.mapper.response;

import br.com.hadryan.watermelon.product.mapper.response.ProductResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItemResponse {

    private Long id;

    private Integer quantity;

    @Positive
    private BigDecimal value;

    @NotNull
    private ProductResponse product;

}
