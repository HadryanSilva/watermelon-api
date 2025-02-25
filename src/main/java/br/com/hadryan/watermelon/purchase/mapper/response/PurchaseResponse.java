package br.com.hadryan.watermelon.purchase.mapper.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PurchaseResponse {

    private Long id;

    private List<PurchaseItemResponse> items;

    private BigDecimal totalValue;

}
