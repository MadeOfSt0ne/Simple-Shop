package example.Simple.Shop.service.user;

import example.Simple.Shop.model.notification.Notification;

import java.util.List;

public interface UserNotificationService {

    List<Notification> getNotifications(Long recipientId, int from, int size);
}
