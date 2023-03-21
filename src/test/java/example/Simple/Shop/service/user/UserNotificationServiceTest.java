package example.Simple.Shop.service.user;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.NotificationRepository;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserNotificationServiceTest {
    private final UserNotificationService service;
    private final UserRepository userRepo;
    private final NotificationRepository notificationRepo;

    @Autowired
    UserNotificationServiceTest(UserNotificationService service, UserRepository userRepo, NotificationRepository notificationRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.notificationRepo = notificationRepo;
    }

    User user = new User();
    Notification notification = new Notification();

    @BeforeEach
    void setUp() {
        user.setUsername("author");
        user.setEmail("sada@addasd.com");
        user.setPassword("ssss");
        user.setRole(Role.ADMIN);
        userRepo.save(user);

        notification.setRecipient(user);
        notification.setTitle("title");
        notification.setText("message");
        notification.setCreated(LocalDate.now());
        notificationRepo.save(notification);
    }

    @Test
    void getNotifications() {
        List<Notification> list = service.getNotifications(1L, 0, 10);
        assertEquals(1, list.size());
        assertEquals("message", list.get(0).getText());
    }
}