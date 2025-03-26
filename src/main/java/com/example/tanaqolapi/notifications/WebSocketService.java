package com.example.tanaqolapi.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessageToUser(String userId, Object message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(message);
            messagingTemplate.convertAndSendToUser(userId, "/queue/ride-requests", jsonMessage);
            System.out.println("Message json " + jsonMessage);
            System.out.println("Message sent successfully to user: " + userId);
        } catch (Exception e) {
            System.err.println("Error sending WebSocket message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}