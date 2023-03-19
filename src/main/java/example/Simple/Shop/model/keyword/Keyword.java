package example.Simple.Shop.model.keyword;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "word")
    private String word;
}
