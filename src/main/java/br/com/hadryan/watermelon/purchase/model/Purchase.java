package br.com.hadryan.watermelon.purchase.model;

import br.com.hadryan.watermelon.finance.model.Account;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseItem> items;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalValue;

    @Timestamp
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}
