package example.Simple.Shop.controller.user;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.service.user.UserNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/user/notifications")
@RequiredArgsConstructor
public class UserNotificationController {

    private final UserNotificationService service;

    @GetMapping
    public List<Notification> getNotifications(@RequestParam Long userId,
                                      @RequestParam(value = "from", required = false, defaultValue = "0") int from,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        log.info("Get notifications for user {}", userId);
        return service.getNotifications(userId, from, size);
    }
}
