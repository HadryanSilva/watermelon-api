package br.com.hadryan.watermelon.order.model;

import br.com.hadryan.watermelon.customer.model.Customer;
import br.com.hadryan.watermelon.finance.model.Account;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private BigDecimal pricePerKg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalValue;

    @Column(nullable = false)
    private Double totalWeight;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}
