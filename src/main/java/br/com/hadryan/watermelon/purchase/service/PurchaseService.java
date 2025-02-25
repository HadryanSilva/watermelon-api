package br.com.hadryan.watermelon.purchase.service;

import br.com.hadryan.watermelon.exception.NotFoundException;
import br.com.hadryan.watermelon.finance.model.Account;
import br.com.hadryan.watermelon.finance.repository.AccountRepository;
import br.com.hadryan.watermelon.product.repository.ProductRepository;
import br.com.hadryan.watermelon.purchase.mapper.PurchaseItemMapper;
import br.com.hadryan.watermelon.purchase.mapper.PurchaseMapper;
import br.com.hadryan.watermelon.purchase.mapper.request.PurchaseItemRequest;
import br.com.hadryan.watermelon.purchase.mapper.request.PurchaseRequest;
import br.com.hadryan.watermelon.purchase.mapper.response.PurchaseResponse;
import br.com.hadryan.watermelon.purchase.model.PurchaseItem;
import br.com.hadryan.watermelon.purchase.repository.PurchaseItemRepository;
import br.com.hadryan.watermelon.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseItemMapper purchaseItemMapper;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public List<PurchaseResponse> findAll(int page, int size) {
        log.info("Finding all purchases");

        return purchaseRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(purchaseMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    public PurchaseResponse findById(Long id) {
        log.info("Finding purchase by id: {}", id);
        return purchaseRepository.findById(id)
                .map(purchaseMapper::modelToResponse)
                .orElseThrow();
    }

    @Transactional
    public PurchaseResponse save(PurchaseRequest request) {
        log.info("Saving purchase");
        var purchaseToSave = purchaseMapper.requestToModel(request);
        purchaseToSave.setItems(saveItems(request.getItems()));
        purchaseToSave.setAccount(validateAccount(request.getAccountId()));
        var savedPurchase = purchaseRepository.save(purchaseToSave);
        updateProductStock(request.getItems());
        updateAccountBalance(request);
        return purchaseMapper.modelToResponse(savedPurchase);
    }

    private Account validateAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    private List<PurchaseItem> saveItems(List<PurchaseItemRequest> products) {
        var items = products.stream()
                .map(purchaseItemMapper::requestToModel)
                .peek(item -> fillProduct(products, item))
                .toList();
        return purchaseItemRepository.saveAll(items);
    }

    private void fillProduct(List<PurchaseItemRequest> products, PurchaseItem purchaseItem) {
        products.forEach(item -> {
            var product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            purchaseItem.setProduct(product);
        });
    }

    private void updateProductStock(List<PurchaseItemRequest> products) {
        products.forEach(item -> {
            var product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        });
    }

    private void updateAccountBalance(PurchaseRequest request) {
        log.info("Updating account balance");
        var account = validateAccount(request.getAccountId());
        account.setExpenses(account.getExpenses().add(request.getTotalValue()));
        account.setBalance(account.getBalance().subtract(request.getTotalValue()));
        accountRepository.save(account);
    }


}
