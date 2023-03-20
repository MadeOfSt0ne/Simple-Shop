package example.Simple.Shop.model.discount;

import example.Simple.Shop.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    @Formula("SELECT * FROM product_discounts d WHERE d.discount_id = id" +
            "LEFT JOIN products p ON d.product_id = p.product_id")
    private List<Product> products;
    @Column(name = "size")
    private double value;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate start;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate end;
}
