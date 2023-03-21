package example.Simple.Shop.repository;

import example.Simple.Shop.model.specification.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    List<Specification> findSpecificationsByProductId(Long productId);
    void deleteSpecificationByIdAndProductId(Long specificationId, Long productId);
}
