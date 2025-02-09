package com.example.tanaqolapi.repository;

import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.services.dto.VehicleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle , UUID> {


    Page<VehicleResponseDTO> findAllByOrderByIdDesc(Pageable pageable);
    boolean existsByLicensePlate(String licensePlate);
}
