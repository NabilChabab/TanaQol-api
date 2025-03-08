package com.example.tanaqolapi.model;


import com.example.tanaqolapi.model.enums.RideStatus;
import com.example.tanaqolapi.model.enums.RideType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RideType rideType;

    private String pickupAddress;
    private String destinationAddress;

    private double pickupLat;
    private double pickupLng;
    private double destinationLat;
    private double destinationLng;

    private LocalDateTime pickupTime;
    private double price;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private AppUser customer;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private AppUser driver;
}
