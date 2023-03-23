package example.Simple.Shop.model.organization;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.List;

/**
 * Организация
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "organizations")
public class Organization {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Название организации
     */
    @Column(name = "name")
    private String name;
    /**
     * Описание организации
     */
    @Column(name = "description")
    private String description;
    /**
     * Адрес логотипа
     */
    @Column(name = "logo")
    private String logoUrl;
    /**
     * Продукты, относящиеся к организации
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Formula("SELECT * FROM products p WHERE p.organization_id = id")
    private List<Product> products;
    /**
     * Владелец организации
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    /**
     * Заблокирована ли организация (может ли вести деятельность)
     */
    @Column(name = "blocked")
    private boolean isBlocked;
}
