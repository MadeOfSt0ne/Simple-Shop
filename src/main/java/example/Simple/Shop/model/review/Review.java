package example.Simple.Shop.model.review;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Отзыв на продукт
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reviews")
public class Review {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Продукт, к которому относится отзыв
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    /**
     * Пользователь, автор отзыва
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * Дата создания отзыва
     */
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    /**
     * Содержание отзыва
     */
    @Column(name = "text")
    private String text;
}
