package example.Simple.Shop.repository;

import example.Simple.Shop.model.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Idea выделяет как ошибку, но этот запрос работает так, как нужно.
     * Возвращает список продуктов, ожидающих модерации
     */
    @Query(nativeQuery = true, value = "SELECT * FROM products p WHERE p.blocked = TRUE ")
    List<Product> getBlockedProducts(Pageable pageable);

    /**
     * Возвращает продукт по его идентификатору
     */
    Product getProductById(Long productId);
}
