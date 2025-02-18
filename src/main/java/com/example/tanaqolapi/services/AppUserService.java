package com.example.tanaqolapi.services;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.services.dto.AppUserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUserService {

    Page<AppUserResponseDTO> findAll(Pageable pageable);
}
