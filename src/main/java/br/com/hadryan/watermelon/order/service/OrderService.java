package br.com.hadryan.watermelon.order.service;

import br.com.hadryan.watermelon.customer.model.Customer;
import br.com.hadryan.watermelon.customer.repository.CustomerRepository;
import br.com.hadryan.watermelon.exception.NotFoundException;
import br.com.hadryan.watermelon.finance.model.Account;
import br.com.hadryan.watermelon.finance.repository.AccountRepository;
import br.com.hadryan.watermelon.order.mapper.OrderMapper;
import br.com.hadryan.watermelon.order.mapper.request.OrderRequest;
import br.com.hadryan.watermelon.order.mapper.response.OrderResponse;
import br.com.hadryan.watermelon.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<OrderResponse> findAll(int page, int size) {
        log.info("Finding all orders");
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::modelToResponse)
                .toList();
    }

    public OrderResponse findById(Long id) {
        log.info("Finding order by id: {}", id);
        return repository.findById(id)
                .map(mapper::modelToResponse)
                .orElseThrow();
    }

    public OrderResponse save(OrderRequest request) {
        log.info("Saving order...");
        var orderToSave = mapper.requestToModel(request);
        orderToSave.setCustomer(validateCustomer(request.getCustomerId()));
        orderToSave.setAccount(validateAccount(request.getAccountId()));
        var savedOrder = repository.save(orderToSave);
        updateAccountBalance(request);
        return mapper.modelToResponse(savedOrder);
    }

    public void update(Long id, OrderRequest request) {
        log.info("Updating order with id: {}", id);
        var orderToUpdate = repository.findById(id)
                .orElseThrow();
        if (request.getPricePerKg() != null) {
            orderToUpdate.setPricePerKg(request.getPricePerKg());
        }
        if (request.getTotalValue() != null) {
            orderToUpdate.setTotalValue(request.getTotalValue());
        }
        if (request.getTotalWeight() != null) {
            orderToUpdate.setTotalWeight(request.getTotalWeight());
        }
        repository.save(orderToUpdate);
        log.info("Order with id {} updated successfully", id);
    }

    private Account validateAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    private Customer validateCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    private void updateAccountBalance(OrderRequest request) {
        log.info("Updating account balance...");
        var account = validateAccount(request.getAccountId());
        account.setRevenues(account.getRevenues().add(request.getTotalValue()));
        account.setBalance(account.getBalance().add(request.getTotalValue()));
        accountRepository.save(account);
    }

}
