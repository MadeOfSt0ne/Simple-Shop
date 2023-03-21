package example.Simple.Shop.model.organization;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logoUrl;
    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Formula("SELECT * FROM products p WHERE p.organization_id = id")
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @Column(name = "blocked")
    private boolean isBlocked;
}
