package example.Simple.Shop.model.review;

import example.Simple.Shop.model.product.Product;
import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    @Column(name = "text")
    private String text;
}