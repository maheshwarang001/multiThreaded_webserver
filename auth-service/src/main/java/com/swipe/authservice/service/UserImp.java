package com.swipe.authservice.service;

import com.swipe.authservice.model.User;
import com.swipe.authservice.model.UserCredential;

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
