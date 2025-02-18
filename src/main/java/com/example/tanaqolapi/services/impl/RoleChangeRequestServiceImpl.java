package com.example.tanaqolapi.services.impl;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.model.enums.Role;
import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import com.example.tanaqolapi.repository.AppUserRepository;
import com.example.tanaqolapi.repository.RoleChangeRequestRepository;
import com.example.tanaqolapi.services.RoleChangeRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RoleChangeRequestServiceImpl implements RoleChangeRequestService {

    private final RoleChangeRequestRepository roleChangeRequestRepository;
    private final AppUserRepository appUserRepository;

    public RoleChangeRequestServiceImpl(RoleChangeRequestRepository roleChangeRequestRepository, AppUserRepository appUserRepository) {
        this.roleChangeRequestRepository = roleChangeRequestRepository;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public RoleChangeRequest sendRequest(RoleChangeRequest roleChangeRequest , UUID appUserId) {
        AppUser user = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.CUSTOMER) {
            throw new RuntimeException("Only customers can request to become drivers");
        }

        roleChangeRequest.setUser(user);
        roleChangeRequest.setStatus(RoleRequestStatus.PENDING);
        return roleChangeRequestRepository.save(roleChangeRequest);
    }



    @Override
    public void updateStatus(UUID requestId) {

    }

    @Override
    public List<RoleChangeRequest> findPendingRequests() {
        return roleChangeRequestRepository.findByStatus(RoleRequestStatus.PENDING);
    }
}
