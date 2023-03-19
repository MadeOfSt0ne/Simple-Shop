package example.Simple.Shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Organization organization;
    private BigDecimal price;
    private long warehouseAmount;
    private List<Discount> discounts;
    private List<Review> reviews;
    private Set<String> keyWords;
    private List<String> specifications;
    private List<Integer> marks;
}
