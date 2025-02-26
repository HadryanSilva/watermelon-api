package br.com.hadryan.watermelon.finance.mapper;

import br.com.hadryan.watermelon.finance.mapper.request.AccountRequest;
import br.com.hadryan.watermelon.finance.mapper.response.AccountResponse;
import br.com.hadryan.watermelon.finance.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account requestToModel(AccountRequest request);

    AccountResponse modelToResponse(Account model);

}
