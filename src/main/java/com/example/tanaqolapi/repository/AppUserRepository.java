package com.example.tanaqolapi.repository;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.enums.Role;
import com.example.tanaqolapi.services.dto.AppUserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<AppUser> findByUsernameOrEmail(String username, String email);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

    List<AppUser> findAppUsersByRole(Role role);
}
