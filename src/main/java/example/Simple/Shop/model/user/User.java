package example.Simple.Shop.model.user;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.purchase.Purchase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.List;

/**
 * Пользователь
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
    @Column(name = "username")
    private String username;
    /**
     * Электронная почта пользователя
     */
    @Column(name = "email")
    private String email;
    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;
    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private Role role;
    /**
     * Баланс средств на счете
     */
    @Column(name = "balance")
    private BigDecimal balance;
    /**
     * Список организаций, добавленных пользователем
     */
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Formula("SELECT * FROM organizations o WHERE o.owner_id = id")
    @ToString.Exclude
    private List<Organization> organizations;
    /**
     * История покупок
     */
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    @Formula("SELECT * FROM purchases p WHERE p.buyer_id = id")
    @ToString.Exclude
    private List<Purchase> purchaseHistory;
    /**
     * Заблокирован пользователь или нет
     */
    @Column(name = "blocked")
    private boolean isBlocked;
}
