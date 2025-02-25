package br.com.hadryan.watermelon.finance.service;

import br.com.hadryan.watermelon.exception.NotFoundException;
import br.com.hadryan.watermelon.finance.mapper.AccountMapper;
import br.com.hadryan.watermelon.finance.mapper.request.AccountRequest;
import br.com.hadryan.watermelon.finance.mapper.response.AccountResponse;
import br.com.hadryan.watermelon.finance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountService {

    private final AccountRepository repository;
    private final AccountMapper mapper;

    public AccountResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::modelToResponse)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public AccountResponse save(AccountRequest request) {
        log.info("Saving account: {}", request.getName());
        var account = repository.save(mapper.requestToModel(request));
        return mapper.modelToResponse(account);
    }

}
