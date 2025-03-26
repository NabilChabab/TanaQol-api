package com.example.tanaqolapi.services;

import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import com.example.tanaqolapi.services.dto.RoleRequestResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RoleChangeRequestService {
    RoleChangeRequest sendRequest(RoleChangeRequest roleChangeRequest , UUID appUserId);

    void updateStatus(UUID requestId , RoleRequestStatus status);

    Page<RoleRequestResponseDTO> findAllRequests(Pageable pageable);
}
