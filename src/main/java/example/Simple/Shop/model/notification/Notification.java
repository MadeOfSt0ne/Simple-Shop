package example.Simple.Shop.model.notification;

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
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @Column(name = "title")
    private String title;
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    @Column(name = "text")
    private String text;
}
