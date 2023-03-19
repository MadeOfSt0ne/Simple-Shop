package example.Simple.Shop.repository;

import example.Simple.Shop.model.purchase.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> getPurchasesByBuyerId(Long buyerId, Pageable pageable);
}
