package com.java.strategy.controller;

import com.java.strategy.model.enums.NotificationType;
import com.java.strategy.model.request.NotificationRequest;
import com.java.strategy.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private List<NotificationService> notificationServices;

    public NotificationController(List<NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        NotificationService notificationService = this.findNotificationService(notificationRequest.type());
        return ResponseEntity.ok(notificationService.send(notificationRequest));
    }

    private NotificationService findNotificationService(NotificationType type) {
        return notificationServices.stream()
                .filter(notificationService -> notificationService.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Notification type not implemented"));
    }
}
