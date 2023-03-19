package example.Simple.Shop.model.organization;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    @OneToMany
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @Column(name = "blocked")
    private boolean isBlocked;
}
