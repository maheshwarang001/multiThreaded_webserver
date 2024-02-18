package com.swipe.authservice.service;


import com.swipe.authservice.factory.UserFactoryProducer;
import com.swipe.authservice.model.User;
import com.swipe.authservice.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserImp {

    @Autowired
    UserFactoryProducer userFactoryProducer;


    @Override
    public User userExists(String username) {

         return userFactoryProducer.get_factory("mysql").getDataSource().userExists(username);

    }

    @Override
    public UserCredential userCredentialExists(String username){
        User u = userExists(username);
        return new UserCredential(u.getEmail(),u.getPwd());
    }

    @Override
    public String createUser(User user) {

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
