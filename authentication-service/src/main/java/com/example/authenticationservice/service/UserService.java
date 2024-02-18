package com.example.authenticationservice.service;


import com.example.authenticationservice.factory.UserFactoryProducer;
import com.example.authenticationservice.model.User;
import com.example.authenticationservice.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserImp {

    @Autowired
    UserFactoryProducer userFactoryProducer;


    @Override
    public User userExists(String username) {

        if(username.isEmpty()) return null;

        return userFactoryProducer.get_factory("mysql").getDataSource().userExists(username);

    }

    @Override
    public UserCredential userCredentialExists(String username){
        if(username.isEmpty()) return null;

        User u = userExists(username);
        return new UserCredential(u.getEmail(),u.getPwd());
    }

    @Override
    public String createUser(User user) {
        if(user == null || user.getEmail().isEmpty() || user.getPwd().isEmpty()) return null;

        System.out.println(user.getPwd());

        try {
            userFactoryProducer.get_factory("mysql").getDataSource().createUser(user);
        }catch (Exception e){
            throw e;
        }

        return "success";

    }

    @Override
    public UUID getUUID(String email) {
        if(email.isEmpty()) return null;
        return userFactoryProducer.get_factory("mysql").getDataSource().getUUIDByEmail(email);
    }

    @Override
    public boolean userLogin(User user) {
        return false;
    }

    @Override
    public void deleteUser(UUID uuid) {

    }

    @Override
    public void updatePwd(UUID uuid) {

    }
}
