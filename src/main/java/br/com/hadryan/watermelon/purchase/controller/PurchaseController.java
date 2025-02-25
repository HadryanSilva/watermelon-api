package br.com.hadryan.watermelon.purchase.controller;

import br.com.hadryan.watermelon.purchase.mapper.request.PurchaseRequest;
import br.com.hadryan.watermelon.purchase.mapper.response.PurchaseResponse;
import br.com.hadryan.watermelon.purchase.model.Purchase;
import br.com.hadryan.watermelon.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    private final PurchaseService service;

    @GetMapping("/list")
    public ResponseEntity<List<PurchaseResponse>> findAll(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<PurchaseResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> save(@RequestBody PurchaseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

}
