package example.Simple.Shop.model.discount;

import example.Simple.Shop.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.List;

/**
 * Скидка
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Заголовок
     */
    @Column(name = "title")
    private String title;
    /**
     * Продукты, на которые распространяется скидка
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @Formula("SELECT * FROM product_discounts d WHERE d.discount_id = id" +
            "LEFT JOIN FETCH products p ON d.product_id = p.product_id")
    private List<Product> products;
    /**
     * Значение
     */
    @Column(name = "size")
    private double value;
    /**
     * Дата начала действия скидки
     */
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate start;
    /**
     * Дата окончания действия скидки
     */
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate end;
}
