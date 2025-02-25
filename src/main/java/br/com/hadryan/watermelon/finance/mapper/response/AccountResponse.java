package br.com.hadryan.watermelon.finance.mapper.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse {

    private Long id;

    private String name;

    private BigDecimal balance;

    private BigDecimal expenses;

    private BigDecimal revenues;

}
