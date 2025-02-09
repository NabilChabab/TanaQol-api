package com.example.tanaqolapi.services;

import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.services.dto.VehicleRequestDTO;
import com.example.tanaqolapi.services.dto.VehicleResponseDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface VehicleService {

    Vehicle save(VehicleRequestDTO vehicleRequestDTO);
    Page<VehicleResponseDTO> findAll(Pageable pageable);
    Optional<VehicleResponseDTO> findById(UUID vehicleId);
}
