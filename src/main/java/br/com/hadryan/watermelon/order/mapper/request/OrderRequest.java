package br.com.hadryan.watermelon.order.mapper.request;

import br.com.hadryan.watermelon.customer.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {

    @NotBlank
    private BigDecimal pricePerKg;

    @NotBlank
    private BigDecimal totalValue;

    @Positive
    @NotNull
    private Double totalWeight;

    @NotNull
    private Customer customer;

}
