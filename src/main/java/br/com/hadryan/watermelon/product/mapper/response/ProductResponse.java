package br.com.hadryan.watermelon.product.mapper.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String barcode;

    private String name;

    private String description;

    private String brand;

    private Integer stock;

}
