package com.example.tanaqolapi.services.impl;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.repository.AppUserRepository;
import com.example.tanaqolapi.services.AppUserService;
import com.example.tanaqolapi.services.dto.AppUserResponseDTO;
import com.example.tanaqolapi.web.mapper.AppUserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    @Override
    public Page<AppUserResponseDTO> findAll(Pageable pageable) {
        Page<AppUser> appUsersPage = appUserRepository.findAll(pageable);
        return appUsersPage.map(appUser -> appUserMapper.toDto(appUser));
    }
}
