package example.Simple.Shop.model.specification;

import example.Simple.Shop.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_specifications")
public class Specification {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
