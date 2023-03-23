package example.Simple.Shop.model.notification;

import example.Simple.Shop.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Уведомление, которое админ может отправлять пользователям
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private long id;
    /**
     * Получатель
     */
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    /**
     * Заголовок
     */
    @Column(name = "title")
    private String title;
    /**
     * Дата создания
     */
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private LocalDate created;
    /**
     * Содержание уведомления
     */
    @Column(name = "text")
    private String text;
}
