package example.Simple.Shop.model.specification;

import example.Simple.Shop.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Характеристика продукта
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_specifications")
public class Specification {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Имя характеристики
     */
    @Column(name = "name")
    private String name;
    /**
     * Значение характеристики
     */
    @Column(name = "description")
    private String value;
    /**
     * Продукт, к которому относится характеристика
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
