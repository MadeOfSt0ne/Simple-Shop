package example.Simple.Shop.model.product;

import example.Simple.Shop.model.discount.Discount;
import example.Simple.Shop.model.keyword.Keyword;
import example.Simple.Shop.model.mark.Mark;
import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.review.Review;
import example.Simple.Shop.model.specification.Specification;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "warehouse_amount")
    private long warehouseAmount;
    @Column(name = "blocked")
    private boolean isBlocked;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    @ToString.Exclude
    @Formula("SELECT * FROM product_discounts d WHERE d.product_id = id" +
            "LEFT JOIN FETCH discounts s ON d.discount_id = s.discount_id")
    private List<Discount> discounts;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @ToString.Exclude
    @Formula("SELECT * FROM reviews r WHERE r.product_id = id")
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @ToString.Exclude
    @Formula("SELECT * FROM marks m WHERE m.product_id = id")
    private List<Mark> marks;
    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @Formula("SELECT * FROM product_keywords p WHERE p.product_id = id" +
            "LEFT JOIN FETCH keywords k ON p.keyword_id = k.keyword_id")
    private List<Keyword> keywords;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @ToString.Exclude
    @Formula("SELECT * FROM product_specifications p WHERE p.product_id = id")
    private List<Specification> specifications;

}
