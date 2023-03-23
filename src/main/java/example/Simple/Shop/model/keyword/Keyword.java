package example.Simple.Shop.model.keyword;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ключевое слово
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "keywords")
public class Keyword {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Значение
     */
    @Column(name = "word")
    private String word;
}
