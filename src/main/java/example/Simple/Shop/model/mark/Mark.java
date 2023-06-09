package example.Simple.Shop.model.mark;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Оценка продукта
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "marks")
public class Mark {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Продукт
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    /**
     * Пользователь-автор
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * Дата создания
     */
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    /**
     * Значение
     */
    @Column(name = "mark")
    private Integer value;
}
