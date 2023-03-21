package example.Simple.Shop.repository;

import example.Simple.Shop.model.specification.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
}
