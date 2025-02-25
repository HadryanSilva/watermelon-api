package br.com.hadryan.watermelon.purchase.mapper;

import br.com.hadryan.watermelon.purchase.mapper.request.PurchaseRequest;
import br.com.hadryan.watermelon.purchase.mapper.response.PurchaseResponse;
import br.com.hadryan.watermelon.purchase.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Purchase requestToModel(PurchaseRequest request);

    PurchaseResponse modelToResponse(Purchase purchase);

}
