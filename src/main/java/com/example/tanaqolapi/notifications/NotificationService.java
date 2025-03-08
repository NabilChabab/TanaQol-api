package com.example.tanaqolapi.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendNotification(UUID userId, String message) {
        template.convertAndSendToUser(
                userId.toString(), // Assuming userId is the destination user
                "/queue/notifications", // Destination queue
                message
        );
    }
}