package example.Simple.Shop.mappers;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.notification.dto.NotificationDto;
import example.Simple.Shop.model.user.User;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class NotificationMapper {
    public static Notification toNotification(NotificationDto dto, User recipient) {
        Notification notif = new Notification();
        notif.setRecipient(recipient);
        notif.setTitle(dto.getTitle());
        notif.setText(dto.getText());
        notif.setCreated(LocalDate.now());
        return notif;
    }
}
