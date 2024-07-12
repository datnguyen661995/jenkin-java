package com.java.strategy.service.impl;

import com.java.strategy.model.enums.EmployeePosition;
import com.java.strategy.model.enums.NotificationType;
import com.java.strategy.model.request.NotificationRequest;
import com.java.strategy.service.NotificationService;

import java.util.Set;
import java.util.stream.Stream;

public class SmsNotificationServiceImpl implements NotificationService {
    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }

    @Override
    public String send(NotificationRequest notificationRequest) {
        return "SMS notification sent to " + notificationRequest.to();
    }
}
