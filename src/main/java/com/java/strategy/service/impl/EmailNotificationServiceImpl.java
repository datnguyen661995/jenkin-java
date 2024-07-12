package com.java.strategy.service.impl;

import com.java.strategy.model.enums.NotificationType;
import com.java.strategy.model.request.NotificationRequest;
import com.java.strategy.service.NotificationService;

public class EmailNotificationServiceImpl implements NotificationService {
    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "Email notification sent to " + notificationRequest.to();
    }
}
