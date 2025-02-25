package br.com.hadryan.watermelon.customer.service;

import br.com.hadryan.watermelon.customer.mapper.CustomerMapper;
import br.com.hadryan.watermelon.customer.mapper.request.CustomerRequest;
import br.com.hadryan.watermelon.customer.mapper.response.CustomerResponse;
import br.com.hadryan.watermelon.customer.repository.CustomerRepository;
import br.com.hadryan.watermelon.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public List<CustomerResponse> findAll(int page, int size) {
        log.info("Finding all customers");
       return repository.findAll(PageRequest.of(page, size))
               .stream()
               .map(mapper::modelToResponse)
               .collect(Collectors.toList());
    }

    public CustomerResponse findById(Long id) {
        log.info("Finding customer by id: {}", id);
        return repository.findById(id)
                .map(mapper::modelToResponse)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public CustomerResponse save(CustomerRequest request) {
        log.info("Creating customer: {}", request);
        var customer = mapper.requestToModel(request);
        var savedCustomer = repository.save(customer);
        return mapper.modelToResponse(savedCustomer);
    }

    public void update(Long id, CustomerRequest request) {
        log.info("Updating customer: {}", request);
        var customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        if (request.getName() != null) {
            customer.setName(request.getName());
        }
        if (request.getEmail() != null) {
            customer.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            customer.setPhone(request.getPhone());
        }
        repository.save(customer);
    }

    public void delete(Long id) {
        log.info("Deleting customer by id: {}", id);
        var customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        repository.delete(customer);
    }

}
