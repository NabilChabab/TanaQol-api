package com.example.tanaqolapi.services.dto;

import com.example.tanaqolapi.model.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponseDTO {

    private String brand;
    private String licensePlate;
    private VehicleType vehicleType;
    private String driverName;
    private String vehicleImage;

}
