package com.example.tanaqolapi.notifications;

import com.example.tanaqolapi.model.Ride;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private WebSocketService webSocketService;

    public void sendRideRequestNotification(UUID driverId, Ride ride) {
        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("rideId", ride.getId().toString());
            notification.put("pickupLocation", ride.getPickupAddress());
            notification.put("destination", ride.getDestinationAddress());
            notification.put("price", ride.getPrice());

            System.out.println("Sending notification to driver: " + driverId);
            System.out.println("Message json " + new ObjectMapper().writeValueAsString(notification));

            // The path should be: /user/{userId}/queue/ride-requests
            webSocketService.sendMessageToUser(driverId.toString(), notification);
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
            e.printStackTrace();
        }
    }
}