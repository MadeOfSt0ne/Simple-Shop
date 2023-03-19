package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.notification.dto.NotificationDto;

public interface AdminNotificationService {

    Notification sendNotification(NotificationDto notification);
}
