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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "seller_id")
    private Organization seller;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "date_time")
    private LocalDateTime buyTime;
    @Column(name = "amount")
    private int amount;
}
