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

/**
 * Сервис для отправки уведомлений пользователям
 */
@Service
@RequiredArgsConstructor
public class AdmNotificationServiceImpl implements AdminNotificationService {

    private final NotificationRepository notificationRepo;
    private final UserRepository userRepo;

    /**
     * Метод для отправки сообщения пользователю
     * Старый метод getById(id).orElseThrow() заменили на новый getReferenceById(id). Новый должен кидать
     * EntityNotFoundException если объекта нет в бд, но не кидает =( Поэтому метод доходит до момента сохранения
     * уведомления в бд и уже бд кидает свое исключение DataIntegrityViolationException. Замапил его в 404.
     */
    @Override
    public Notification sendNotification(NotificationDto dto) {
        User recipient = userRepo.getReferenceById(dto.getRecipientId());
        Notification notif = NotificationMapper.toNotification(dto, recipient);
        return notificationRepo.save(notif);
    }
}
