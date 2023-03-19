package example.Simple.Shop.model;

import example.Simple.Shop.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String name;
    private String description;
    private String logoUrl;
    private List<Product> products;
    private User owner;
    private boolean isBlocked;
}
