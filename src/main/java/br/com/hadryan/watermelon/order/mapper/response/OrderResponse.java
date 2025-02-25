package br.com.hadryan.watermelon.order.mapper.response;

import br.com.hadryan.watermelon.customer.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {

    private Long id;

    private BigDecimal pricePerKg;

    private BigDecimal totalValue;

    private Double totalWeight;

    private Customer customer;

}
