package br.com.hadryan.watermelon.product.mapper.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    private String barcode;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String brand;

    @NotBlank
    private Integer stock;

}
