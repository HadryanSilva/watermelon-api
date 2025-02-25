package br.com.hadryan.watermelon.order.controller;

import br.com.hadryan.watermelon.order.mapper.request.OrderRequest;
import br.com.hadryan.watermelon.order.mapper.response.OrderResponse;
import br.com.hadryan.watermelon.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;

    @GetMapping("/list")
    public ResponseEntity<List<OrderResponse>> findAll(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<OrderResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("id") Long id,
                                       @RequestBody OrderRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

}
