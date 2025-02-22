package com.example.tanaqolapi.repository;

import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleChangeRequestRepository extends JpaRepository<RoleChangeRequest, UUID> {

    Page<RoleChangeRequest> findByStatus(RoleRequestStatus status , Pageable pageable);
}
