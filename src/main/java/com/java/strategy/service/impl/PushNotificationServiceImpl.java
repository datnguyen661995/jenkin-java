package com.java.strategy.service.impl;

import com.java.strategy.model.enums.NotificationType;
import com.java.strategy.model.request.NotificationRequest;
import com.java.strategy.service.NotificationService;

public class PushNotificationServiceImpl implements NotificationService {
    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "Push notification sent to " + notificationRequest.to();
    }
}
