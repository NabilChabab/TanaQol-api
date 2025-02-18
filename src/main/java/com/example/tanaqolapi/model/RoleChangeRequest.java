package com.example.tanaqolapi.model;


import com.example.tanaqolapi.model.enums.RoleRequestStatus;
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
public class RoleChangeRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private String brand;
    private String licensePlate;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private String insuranceProof;
    private String licenseProof;
    private String vehicleImage;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private RoleRequestStatus status = RoleRequestStatus.PENDING;
}
