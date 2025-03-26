package com.example.tanaqolapi.services.dto;

import com.example.tanaqolapi.model.enums.RideType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDTO {
    private String pickupAddress;
    private String destinationAddress;
    private double pickupLat;
    private double pickupLng;
    private double destinationLat;
    private double destinationLng;
    private double price;
    private RideType rideType;
    private UUID customerId;
    private UUID driverId;
}