package example.Simple.Shop.repository;

import example.Simple.Shop.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
