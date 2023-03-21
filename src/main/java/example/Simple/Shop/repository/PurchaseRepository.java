package example.Simple.Shop.repository;

import example.Simple.Shop.model.purchase.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> getPurchasesByBuyerId(Long buyerId, Pageable pageable);
    Purchase getPurchaseByProductIdAndBuyerId(Long productId, Long buyerId);
    Purchase getPurchaseById(Long purchaseId);
}
