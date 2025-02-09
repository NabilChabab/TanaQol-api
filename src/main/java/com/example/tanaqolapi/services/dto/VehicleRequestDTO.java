package com.example.tanaqolapi.services.dto;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleRequestDTO {

    @NotBlank(message = "La marque du véhicule est obligatoire")
    private String brand;

    @NotBlank(message = "La plaque d'immatriculation est obligatoire")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Le format de la plaque d'immatriculation est invalide")
    private String licensePlate;

    @NotNull(message = "Le type de véhicule est obligatoire")
    private VehicleType vehicleType;

    @NotNull(message = "L'identifiant du chauffeur est obligatoire")
    private UUID driverId;
}
