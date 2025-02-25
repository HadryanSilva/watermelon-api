package br.com.hadryan.watermelon.purchase.repository;

import br.com.hadryan.watermelon.purchase.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
}
