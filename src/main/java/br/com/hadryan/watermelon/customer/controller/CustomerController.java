package br.com.hadryan.watermelon.customer.controller;

import br.com.hadryan.watermelon.customer.mapper.request.CustomerRequest;
import br.com.hadryan.watermelon.customer.mapper.response.CustomerResponse;
import br.com.hadryan.watermelon.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> findAll(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("id") Long id,
                                       @RequestBody CustomerRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
