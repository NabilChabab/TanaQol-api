package com.example.tanaqolapi.web.api.v1.users;


import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.services.AppUserService;
import com.example.tanaqolapi.services.dto.AppUserResponseDTO;
import com.example.tanaqolapi.web.mapper.AppUserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    public AppUserController(AppUserService appUserService, AppUserMapper appUserMapper) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<AppUserResponseDTO>> findAll(Pageable pageable) {
        Page<AppUserResponseDTO> appUserResponseDTOPage = appUserService.findAll(pageable);
        return new ResponseEntity<>(appUserResponseDTOPage, HttpStatus.OK);
    }
}
