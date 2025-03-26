package com.example.tanaqolapi.web.api.v1.ride;

import com.example.tanaqolapi.model.Ride;
import com.example.tanaqolapi.model.enums.RideStatus;
import com.example.tanaqolapi.repository.RideRepository;
import com.example.tanaqolapi.services.dto.RideRequestDTO;
import com.example.tanaqolapi.services.impl.RideServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rides")
@CrossOrigin("*")
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideServiceImpl rideService;

    @PostMapping("/save")
    public ResponseEntity<Ride> createRide(@RequestBody RideRequestDTO rideRequest) {
        Ride savedRide = rideService.createRide(rideRequest);
        return ResponseEntity.ok(savedRide);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable UUID id) {
        return rideRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Ride>> getRidesByCustomer(@PathVariable UUID customerId) {
        List<Ride> rides = rideService.getRidesByCustomerId(customerId);
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Ride>> getRidesByDriver(@PathVariable UUID driverId) {
        List<Ride> rides = rideService.getRidesByDriverId(driverId);
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ride>> getRidesByStatus(@PathVariable RideStatus status) {
        List<Ride> rides = rideService.getRidesByStatus(status);
        return ResponseEntity.ok(rides);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Ride> updateStatus(@PathVariable UUID id, @RequestParam RideStatus status) {
        Ride updatedRide = rideService.updateRideStatus(id, status);
        if (updatedRide != null) {
            return ResponseEntity.ok(updatedRide);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriver(@PathVariable UUID id, @PathVariable UUID driverId) {
        Ride updatedRide = rideService.assignDriver(id, driverId);
        if (updatedRide != null) {
            return ResponseEntity.ok(updatedRide);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{rideId}/accept")
    public ResponseEntity<Ride> acceptRide(@PathVariable UUID rideId) {
        Ride ride = rideService.acceptRide(rideId);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{rideId}/refuse")
    public ResponseEntity<Ride> refuseRide(@PathVariable UUID rideId) {
        Ride ride = rideService.refuseRide(rideId);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}