package com.java.strategy.model.request;

import com.java.strategy.model.enums.NotificationType;

public record NotificationRequest(String to, NotificationType type) {

    @Override
    public String toString() {
        return "{" +
                "\"to\": \"" + to + "\"," +
                "\"type\": \"" + type + "\"" +
                '}';
    }
}
