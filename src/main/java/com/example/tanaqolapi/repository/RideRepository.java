package com.example.tanaqolapi.repository;

import com.example.tanaqolapi.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RideRepository extends JpaRepository<Ride, UUID> {
}
