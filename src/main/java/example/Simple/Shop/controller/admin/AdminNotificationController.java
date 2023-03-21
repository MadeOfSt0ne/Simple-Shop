package example.Simple.Shop.controller.admin;

import example.Simple.Shop.model.notification.Notification;
import example.Simple.Shop.model.notification.dto.NotificationDto;
import example.Simple.Shop.service.admin.AdminNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/admin/notification")
@RequiredArgsConstructor
public class AdminNotificationController {

    private final AdminNotificationService service;

    @PostMapping
    public Notification sendNotification(@RequestBody NotificationDto dto) {
        log.info("Send notification {}", dto);
        return service.sendNotification(dto);
    }
}
