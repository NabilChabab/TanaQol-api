package com.example.tanaqolapi.web.api.v1.role_request;


import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import com.example.tanaqolapi.services.RoleChangeRequestService;
import com.example.tanaqolapi.services.dto.RoleRequestResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/role-change-requests")
public class RoleChangeRequestController {

    private final RoleChangeRequestService roleChangeRequestService;

    public RoleChangeRequestController(RoleChangeRequestService roleChangeRequestService) {
        this.roleChangeRequestService = roleChangeRequestService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<RoleChangeRequest> requestRoleChange(@PathVariable UUID userId,
                                                               @RequestBody RoleChangeRequest request) {
        return ResponseEntity.ok(roleChangeRequestService.sendRequest(request,userId));
    }

    @GetMapping("/all-pending")
    public ResponseEntity<Page<RoleRequestResponseDTO>> getPendingRequests(Pageable pageable) {
        return ResponseEntity.ok(roleChangeRequestService.findAllRequests(pageable));
    }

    @PutMapping("/{requestId}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable UUID requestId,
            @RequestParam(name = "status") RoleRequestStatus status) {

        roleChangeRequestService.updateStatus(requestId, status);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
