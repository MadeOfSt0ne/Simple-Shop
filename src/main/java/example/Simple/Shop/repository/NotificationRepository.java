package example.Simple.Shop.repository;

import example.Simple.Shop.model.notification.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationsByRecipientId(Long recipientId, Pageable pageable);
}
