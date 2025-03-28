package com.example.tanaqolapi.services.impl;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.RoleChangeRequest;
import com.example.tanaqolapi.model.Vehicle;
import com.example.tanaqolapi.model.enums.Role;
import com.example.tanaqolapi.model.enums.RoleRequestStatus;
import com.example.tanaqolapi.notifications.NotificationService;
import com.example.tanaqolapi.repository.AppUserRepository;
import com.example.tanaqolapi.repository.RoleChangeRequestRepository;
import com.example.tanaqolapi.repository.VehicleRepository;
import com.example.tanaqolapi.services.RoleChangeRequestService;
import com.example.tanaqolapi.services.dto.RoleRequestResponseDTO;
import com.example.tanaqolapi.web.mapper.RoleRequestMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class RoleChangeRequestServiceImpl implements RoleChangeRequestService {

    private final RoleChangeRequestRepository roleChangeRequestRepository;
    private final AppUserRepository appUserRepository;
    private final RoleRequestMapper roleRequestMapper;
    private final VehicleRepository vehicleRepository;
    private final NotificationService notificationService;

    public RoleChangeRequestServiceImpl(RoleChangeRequestRepository roleChangeRequestRepository, AppUserRepository appUserRepository, RoleRequestMapper roleRequestMapper, VehicleRepository vehicleRepository, NotificationService notificationService) {
        this.roleChangeRequestRepository = roleChangeRequestRepository;
        this.appUserRepository = appUserRepository;
        this.roleRequestMapper = roleRequestMapper;
        this.vehicleRepository = vehicleRepository;
        this.notificationService = notificationService;
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
    public void updateStatus(UUID requestId, RoleRequestStatus status) {

        RoleChangeRequest request = roleChangeRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Role change request not found"));

        if (status == RoleRequestStatus.APPROVED) {
            // Save the vehicle automatically
            Vehicle vehicle = Vehicle.builder()
                    .brand(request.getBrand())
                    .licensePlate(request.getLicensePlate())
                    .licenseNumber(request.getLicenseNumber())
                    .licenseExpiryDate(request.getLicenseExpiryDate())
                    .insuranceProof(request.getInsuranceProof())
                    .licenseProof(request.getLicenseProof())
                    .vehicleImage(request.getVehicleImage())
                    .vehicleType(request.getVehicleType())
                    .driver(request.getUser())
                    .build();

            vehicleRepository.save(vehicle);
            request.setStatus(RoleRequestStatus.APPROVED);
            AppUser user = request.getUser();
            user.setRole(Role.DRIVER);
            appUserRepository.save(user);
            roleChangeRequestRepository.save(request);

            // Send notification
//            notificationService.sendNotification(user.getId(), "Your role change request has been approved.");
        } else {
            roleChangeRequestRepository.delete(request);

            // Send notification
//            notificationService.sendNotification(request.getUser().getId(), "Your role change request has been rejected.");
        }
    }

    public Page<RoleRequestResponseDTO> findAllRequests(Pageable pageable) {
        return roleChangeRequestRepository.findAll(pageable).map(roleRequestMapper::toDto);
    }
}
