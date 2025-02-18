package com.example.tanaqolapi.web.mapper;


import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.services.dto.AppUserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser toEntity(AppUserResponseDTO appUserResponseDTO);
    AppUserResponseDTO toDto(AppUser appUser);
}
