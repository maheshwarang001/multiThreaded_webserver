package com.example.profileservice.repository;

import com.example.profileservice.model.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<GeoLocation, UUID> {
    GeoLocation findByGeolocationId(UUID id);
}
