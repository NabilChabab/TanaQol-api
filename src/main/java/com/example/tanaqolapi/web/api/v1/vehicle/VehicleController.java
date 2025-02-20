package com.example.tanaqolapi.web.api.v1.vehicle;


import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.services.VehicleService;
import com.example.tanaqolapi.services.dto.VehicleRequestDTO;
import com.example.tanaqolapi.services.dto.VehicleResponseDTO;
import com.example.tanaqolapi.web.mapper.VehicleMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<VehicleResponseDTO> save(@RequestBody @Valid VehicleRequestDTO vehicleRequestDTO) {
        Vehicle savedvehicle = vehicleService.save(vehicleRequestDTO);
        VehicleResponseDTO vehicleResponseDTO = vehicleMapper.toDTO(savedvehicle);
        return new ResponseEntity<>(vehicleResponseDTO ,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<VehicleResponseDTO>> findAll(Pageable pageable) {
        Page<VehicleResponseDTO> vehicleResponseDTOPage = vehicleService.findAll(pageable);
        return new ResponseEntity<>(vehicleResponseDTOPage, HttpStatus.OK);
    }
}
