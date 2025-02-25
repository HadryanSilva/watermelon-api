package br.com.hadryan.watermelon.product.controller;

import br.com.hadryan.watermelon.product.mapper.request.ProductRequest;
import br.com.hadryan.watermelon.product.mapper.response.ProductResponse;
import br.com.hadryan.watermelon.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> findAll(@RequestParam("page") int page,
                                                         @RequestParam("size") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping
    public ResponseEntity<ProductResponse> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("id") Long id,
                                       @RequestBody ProductRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
