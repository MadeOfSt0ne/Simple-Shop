package example.Simple.Shop.model.purchase;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Покупка (для сохранения в историю)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Продукт
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    /**
     * Организация-продавец
     */
    @OneToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;
    /**
     * Пользователь-покупатель
     */
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    /**
     * Цена продукта
     */
    @Column(name = "price")
    private BigDecimal price;
    /**
     * Дата и время покупки
     */
    @Column(name = "date_time")
    private LocalDateTime buyTime;
    /**
     * Количество купленных единиц продукта
     */
    @Column(name = "amount")
    private int amount;
}
