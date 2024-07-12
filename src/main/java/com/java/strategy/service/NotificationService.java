package com.java.strategy.service;

import com.java.strategy.model.enums.NotificationType;
import com.java.strategy.model.request.NotificationRequest;

public interface NotificationService {
    NotificationType getType();

    String send(NotificationRequest notificationRequest);
}
