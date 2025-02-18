package com.example.tanaqolapi.web.mapper;


import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.services.dto.VehicleRequestDTO;
import com.example.tanaqolapi.services.dto.VehicleResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {


    Vehicle toEntity(VehicleRequestDTO vehicleRequestDTO);

    @Mapping(expression = "java(vehicle.getDriver().getFirstName() + \" \" + vehicle.getDriver().getLastName())", target = "driverName")
    @Mapping(source = "vehicleImage", target = "vehicleImage")
    VehicleResponseDTO toDTO(Vehicle vehicle);


}
