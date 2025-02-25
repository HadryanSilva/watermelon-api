package br.com.hadryan.watermelon.product.service;

import br.com.hadryan.watermelon.exception.NotFoundException;
import br.com.hadryan.watermelon.product.mapper.ProductMapper;
import br.com.hadryan.watermelon.product.mapper.request.ProductRequest;
import br.com.hadryan.watermelon.product.mapper.response.ProductResponse;
import br.com.hadryan.watermelon.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public List<ProductResponse> findAll(int page, int size) {
        log.info("Finding all products");
        return repository.findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::modelToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findById(Long id) {
        log.info("Finding product by id: {}", id);
        return repository.findById(id)
                .map(mapper::modelToResponse)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public ProductResponse save(ProductRequest request) {
        log.info("Saving product: {}", request.getName());
        var product = repository.save(mapper.requestToModel(request));
        return mapper.modelToResponse(product);
    }

    public void update(Long id, ProductRequest request) {
        log.info("Updating product: {}", request.getName());
        var product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }
        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }
        repository.save(product);
    }

    public void delete(Long id) {
        log.info("Deleting product by id: {}", id);
        var product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        repository.delete(product);
    }

}
