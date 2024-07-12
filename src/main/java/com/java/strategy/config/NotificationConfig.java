package com.java.strategy.config;

import com.java.strategy.service.NotificationService;
import com.java.strategy.service.impl.EmailNotificationServiceImpl;
import com.java.strategy.service.impl.PushNotificationServiceImpl;
import com.java.strategy.service.impl.SmsNotificationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class NotificationConfig {
    @Bean
    public NotificationService emailNotificationService() {
        return new EmailNotificationServiceImpl();
    }

    @Bean
    public NotificationService pushNotificationService() {
        return new PushNotificationServiceImpl();
    }

    @Bean
    public NotificationService smsNotificationService() {
        return new SmsNotificationServiceImpl();
    }

    @Bean
    public List<NotificationService> notificationServices() {
        return Arrays.asList(emailNotificationService(), pushNotificationService(), smsNotificationService());
    }
}
