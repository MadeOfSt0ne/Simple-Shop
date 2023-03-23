package example.Simple.Shop.service.user.Impl;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.repository.NotificationRepository;
import example.Simple.Shop.service.user.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotificationServiceImpl implements UserNotificationService {

    private final NotificationRepository notificationRepo;

    /**
     * Просмотр уведомлений (постранично)
     */
    @Override
    public List<Notification> getNotifications(Long recipientId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return notificationRepo.getNotificationsByRecipientId(recipientId, pageable);
    }
}
