package com.example.tanaqolapi.services.impl;

import com.example.tanaqolapi.exceptions.VehicleAlreadyExistException;
import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.repository.AppUserRepository;
import com.example.tanaqolapi.repository.VehicleRepository;
import com.example.tanaqolapi.services.VehicleService;
import com.example.tanaqolapi.services.dto.VehicleRequestDTO;
import com.example.tanaqolapi.services.dto.VehicleResponseDTO;
import com.example.tanaqolapi.web.mapper.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final AppUserRepository appUserRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, AppUserRepository appUserRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Vehicle save(VehicleRequestDTO vehicleRequestDTO) {
        AppUser driver = appUserRepository.findById(vehicleRequestDTO.getDriverId())
                .orElseThrow(() -> new EntityNotFoundException("Chauffeur non trouvé avec l'ID : " + vehicleRequestDTO.getDriverId()));

        boolean vehicleOptional = vehicleRepository.existsByLicensePlate(vehicleRequestDTO.getLicensePlate());
        if (vehicleOptional) {
            throw new VehicleAlreadyExistException("vehicle already exist : " + vehicleRequestDTO.getLicensePlate());
        }

        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequestDTO);
        vehicle.setDriver(driver);

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Page<VehicleResponseDTO> findAll(Pageable pageable) {
        return vehicleRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Optional<VehicleResponseDTO> findById(UUID vehicleId) {
        return Optional.empty();
    }


}
