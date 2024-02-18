package com.example.authenticationservice.service;

import com.example.authenticationservice.model.User;
import com.example.authenticationservice.model.UserCredential;

import java.util.UUID;

public interface UserImp {

    User userExists(String username);
    UserCredential userCredentialExists(String username);
    String createUser(User username);

    UUID getUUID(String email);

    boolean userLogin(User user);

    void deleteUser(UUID uuid);

    void updatePwd(UUID uuid);





}
