package br.com.hadryan.watermelon.order.mapper;

import br.com.hadryan.watermelon.order.mapper.request.OrderRequest;
import br.com.hadryan.watermelon.order.mapper.response.OrderResponse;
import br.com.hadryan.watermelon.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Order requestToModel(OrderRequest request);

    OrderResponse modelToResponse(Order order);

}
