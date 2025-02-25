package br.com.hadryan.watermelon.finance.mapper.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private BigDecimal expenses;

    @NotNull
    private BigDecimal revenues;

}
