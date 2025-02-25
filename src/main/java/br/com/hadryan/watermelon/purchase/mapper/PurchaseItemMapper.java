package br.com.hadryan.watermelon.purchase.mapper;

import br.com.hadryan.watermelon.purchase.mapper.request.PurchaseItemRequest;
import br.com.hadryan.watermelon.purchase.mapper.response.PurchaseItemResponse;
import br.com.hadryan.watermelon.purchase.model.PurchaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    PurchaseItem requestToModel(PurchaseItemRequest request);

    PurchaseItemResponse modelToResponse(PurchaseItem purchaseItem);

}
