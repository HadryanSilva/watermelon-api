package br.com.hadryan.watermelon.product.mapper;

import br.com.hadryan.watermelon.product.mapper.request.ProductRequest;
import br.com.hadryan.watermelon.product.mapper.response.ProductResponse;
import br.com.hadryan.watermelon.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product requestToModel(ProductRequest request);

    ProductResponse modelToResponse(Product product);

}
