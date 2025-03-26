package com.example.tanaqolapi.services.impl;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.Ride;
import com.example.tanaqolapi.model.enums.RideStatus;
import com.example.tanaqolapi.model.enums.RideType;
import com.example.tanaqolapi.model.enums.Role;
import com.example.tanaqolapi.notifications.NotificationService;
import com.example.tanaqolapi.repository.AppUserRepository;
import com.example.tanaqolapi.repository.RideRepository;
import com.example.tanaqolapi.services.RideService;
import com.example.tanaqolapi.services.dto.RideRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    public Ride createRide(RideRequestDTO rideRequest) {
        AppUser customer = userRepository.findById(rideRequest.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        AppUser driver = userRepository.findById(rideRequest.getDriverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        Ride ride = new Ride();
        ride.setPickupAddress(rideRequest.getPickupAddress());
        ride.setDestinationAddress(rideRequest.getDestinationAddress());
        ride.setPickupLat(rideRequest.getPickupLat());
        ride.setPickupLng(rideRequest.getPickupLng());
        ride.setDestinationLat(rideRequest.getDestinationLat());
        ride.setDestinationLng(rideRequest.getDestinationLng());
        ride.setRideType(rideRequest.getRideType());
        ride.setPrice(rideRequest.getPrice());
        ride.setCustomer(customer);
        ride.setDriver(driver);
        ride.setRideStatus(RideStatus.REQUESTED);
        ride.setPickupTime(LocalDateTime.now());

        Ride savedRide = rideRepository.save(ride);

        sendRideRequestToDrivers(savedRide);

        return savedRide;
    }


    private void sendRideRequestToDrivers(Ride ride) {
        List<AppUser> drivers = userRepository.findAppUsersByRole(Role.DRIVER);

        for (AppUser driver : drivers) {
            notificationService.sendRideRequestNotification(driver.getId(), ride);
        }
    }

    public Ride acceptRide(UUID rideId) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);

        if (rideOpt.isPresent()) {
            Ride ride = rideOpt.get();
            ride.setRideStatus(RideStatus.ACCEPTED);
            return rideRepository.save(ride);
        }
        return null;
    }

    public Ride refuseRide(UUID rideId) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);

        if (rideOpt.isPresent()) {
            Ride ride = rideOpt.get();
            ride.setRideStatus(RideStatus.REJECTED);
            return rideRepository.save(ride);
        }
        return null;
    }

    public Optional<Ride> getRideById(UUID id) {
        return rideRepository.findById(id);
    }

    public List<Ride> getRidesByCustomerId(UUID customerId) {
        return rideRepository.findByCustomerId(customerId);
    }

    public List<Ride> getRidesByDriverId(UUID driverId) {
        return rideRepository.findByDriverId(driverId);
    }

    public List<Ride> getRidesByStatus(RideStatus status) {
        return rideRepository.findByRideStatus(status);
    }

    public Ride updateRideStatus(UUID rideId, RideStatus newStatus) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);
        if (rideOpt.isPresent()) {
            Ride ride = rideOpt.get();
            ride.setRideStatus(newStatus);
            return rideRepository.save(ride);
        }
        return null;
    }

    public Ride assignDriver(UUID rideId, UUID driverId) {
        Optional<Ride> rideOpt = rideRepository.findById(rideId);
        Optional<AppUser> driverOpt = userRepository.findById(driverId);

        if (rideOpt.isPresent() && driverOpt.isPresent()) {
            Ride ride = rideOpt.get();
            ride.setDriver(driverOpt.get());
            ride.setRideStatus(RideStatus.ACCEPTED);
            return rideRepository.save(ride);
        }
        return null;
    }

    public double calculatePrice(double distance, RideType rideType) {
        double basePrice = 15.0; // Base price in your currency
        double pricePerKm = 2.5; // Price per km

        double multiplier = 1.0;
        switch (rideType) {
            case PREMIUM:
                multiplier = 1.5;
                break;
            case SHARED:
                multiplier = 0.7;
                break;
            default: // STANDARD
                multiplier = 1.0;
        }

        return (basePrice + (distance * pricePerKm)) * multiplier;
    }
}