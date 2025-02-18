package com.example.tanaqolapi.services;

import com.example.tanaqolapi.model.RoleChangeRequest;

import java.util.List;
import java.util.UUID;

public interface RoleChangeRequestService {
    RoleChangeRequest sendRequest(RoleChangeRequest roleChangeRequest , UUID appUserId);

    void updateStatus(UUID requestId);

    List<RoleChangeRequest> findPendingRequests();
}
