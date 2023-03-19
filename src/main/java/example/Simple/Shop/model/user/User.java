package example.Simple.Shop.model.user;

import example.Simple.Shop.model.Organization;
import example.Simple.Shop.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Пользователь
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Имя пользователя
     */
    private String username;
    /**
     * Электронная почта пользователя
     */
    private String email;
    /**
     * Пароль
     */
    private String password;
    /**
     * Роль пользователя
     */
    private Role role;
    /**
     * Баланс средств на счете
     */
    private BigDecimal balance;
    /**
     * Список организаций, добавленных пользователем
     */
    @OneToMany(mappedBy = "user")
    private Set<Organization> organizations;
    /**
     * История покупок
     */
    @OneToMany(mappedBy = "user")
    private Map<Product, Integer> purchaseHistory;
}
