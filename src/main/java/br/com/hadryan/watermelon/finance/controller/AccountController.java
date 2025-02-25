package br.com.hadryan.watermelon.finance.controller;

import br.com.hadryan.watermelon.finance.mapper.request.AccountRequest;
import br.com.hadryan.watermelon.finance.mapper.response.AccountResponse;
import br.com.hadryan.watermelon.finance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<AccountResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> save(@RequestBody AccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

}
