package example.Simple.Shop.model.specification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
}
