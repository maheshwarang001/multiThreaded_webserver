package com.example.profileservice.dao;

import com.example.profileservice.model.GeoLocation;
import com.example.profileservice.model.Preferences;
import com.example.profileservice.model.UserBiological;
import com.example.profileservice.model.UserDetail;

import java.util.List;
import java.util.UUID;

public interface UserProfileDao {

    void createUserProfile(UserDetail userDetail);

    void updateUser(UserDetail userDetail);

    void deleteProfile(UUID userId);

    UserDetail getUserProfile(UUID userId);


    List<UserDetail> serveUserMetadata( List<UUID> uuids);


}
