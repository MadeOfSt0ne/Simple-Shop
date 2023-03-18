package example.Simple.Shop.model.user;

import example.Simple.Shop.model.Organization;
import example.Simple.Shop.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;
    private String password;
    private BigDecimal balance;
    private Set<Organization> organizations;
    private Map<Product, Integer> purchaseHistory;
}
