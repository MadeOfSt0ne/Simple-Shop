package example.Simple.Shop.repository;

import example.Simple.Shop.model.mark.Mark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findByProductId(Long productId, Pageable pageable);
}
