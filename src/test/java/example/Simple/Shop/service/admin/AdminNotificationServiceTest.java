package example.Simple.Shop.service.admin;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.notification.dto.NotificationDto;
import example.Simple.Shop.model.user.Role;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AdminNotificationServiceTest {

    private final AdminNotificationService service;
    private final UserRepository userRepo;

    @Autowired
    AdminNotificationServiceTest(AdminNotificationService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    User recipient = new User();

    @BeforeEach
    void setUp() {
        recipient.setUsername("username");
        recipient.setEmail("aaa@aaa.com");
        recipient.setPassword("pass");
        recipient.setRole(Role.USER);
        userRepo.save(recipient);
    }

    @Test
    void sendNotification() {
        NotificationDto dto = new NotificationDto(recipient.getId(), "some title", "some message");
        Notification notification = service.sendNotification(dto);
        assertEquals(dto.getText(), notification.getText());
    }

    @Test
    void sendNotificationToNonExistingUser() {
        NotificationDto dto = new NotificationDto(111L, "some title", "some message");
        assertThrows(DataIntegrityViolationException.class, () -> service.sendNotification(dto));
    }
}