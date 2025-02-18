package com.example.tanaqolapi.model;


import com.example.tanaqolapi.model.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    private String brand;
    private String licensePlate;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private String insuranceProof;
    private String licenseProof;
    private String vehicleImage;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "driver_id" , nullable = false)
    private AppUser driver;

}
