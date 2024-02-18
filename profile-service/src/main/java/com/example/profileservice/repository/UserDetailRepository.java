package com.example.profileservice.repository;

import com.example.profileservice.model.GeoLocation;
import com.example.profileservice.model.Preferences;
import com.example.profileservice.model.UserBiological;
import com.example.profileservice.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface UserDetailRepository extends JpaRepository<UserDetail, UUID> {

    UserDetail findByUserId(UUID id);

    @Query("SELECT ud FROM UserDetail ud WHERE ud.userId IN :uuids")
    List<UserDetail> findByUuids(@Param("uuids") List<UUID> uuids);




}
