package com.example.profileservice.service;

import com.example.profileservice.model.Metadata;
import com.example.profileservice.model.UserDetail;

import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

public interface UserServiceImpl {

    void createUserProfile(UserDetail userDetail);
    void updateProfile(UserDetail userDetail);

    void deleteProfile(UUID userId);

    UserDetail getUserProfile(UUID userId);


    List<Metadata> getMetaData(List<UUID> uuidList);

}
