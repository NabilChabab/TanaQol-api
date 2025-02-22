package com.example.tanaqolapi.services.dto;

import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import com.example.tanaqolapi.model.enums.VehicleType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestResponseDTO {
    private UUID id;
    private String brand;
    private String licensePlate;
    private String licenseNumber;
    private LocalDate licenseExpiryDate;
    private String insuranceProof;
    private String licenseProof;
    private String vehicleImage;
    private VehicleType vehicleType;
    private RoleRequestStatus status;
    private String userName;
}
