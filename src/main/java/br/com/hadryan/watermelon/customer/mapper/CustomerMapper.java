package br.com.hadryan.watermelon.customer.mapper;

import br.com.hadryan.watermelon.customer.mapper.request.CustomerRequest;
import br.com.hadryan.watermelon.customer.mapper.response.CustomerResponse;
import br.com.hadryan.watermelon.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer requestToModel(CustomerRequest request);

    CustomerResponse modelToResponse(Customer model);

}
