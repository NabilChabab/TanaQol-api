package com.example.tanaqolapi.web.mapper;

import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.services.dto.RoleRequestResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleRequestMapper {
    RoleRequestMapper INSTANCE = Mappers.getMapper(RoleRequestMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", expression = "java(getFullName(roleChangeRequest.getUser().getFirstName(), roleChangeRequest.getUser().getLastName()))")
    RoleRequestResponseDTO toDto(RoleChangeRequest roleChangeRequest);

    default String getFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
