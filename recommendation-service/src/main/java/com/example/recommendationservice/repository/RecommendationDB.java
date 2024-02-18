package com.example.recommendationservice.repository;

import com.example.recommendationservice.entity.UserCityDB;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecommendationDB extends JpaRepository<UserCityDB, UUID> {

    @Query("SELECT userId FROM UserCityDB WHERE city = :city")
    List<UUID> findByCity(@Param("city") String city, Pageable pageable);
}
