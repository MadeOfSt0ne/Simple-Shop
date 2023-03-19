package example.Simple.Shop.service.admin.Impl;

import example.Simple.Shop.mappers.NotificationMapper;
import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.notification.dto.NotificationDto;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.NotificationRepository;
import example.Simple.Shop.repository.UserRepository;
import example.Simple.Shop.service.admin.AdminNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmNotificationServiceImpl implements AdminNotificationService {

    private final NotificationRepository notificationRepo;
    private final UserRepository userRepo;

    @Override
    public Notification sendNotification(NotificationDto dto) {
        User recipient = userRepo.getReferenceById(dto.getRecipientId());
        Notification notif = NotificationMapper.toNotification(dto, recipient);
        return notificationRepo.save(notif);
    }
}
