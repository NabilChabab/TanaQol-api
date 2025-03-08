package com.example.tanaqolapi.repository;

import com.example.tanaqolapi.model.Ride;
import com.example.tanaqolapi.model.enums.RideStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RideRepository extends JpaRepository<Ride, UUID> {

    List<Ride> findByCustomerId(UUID customerId);
    List<Ride> findByDriverId(UUID driverId);
    List<Ride> findByRideStatus(RideStatus status);

}
