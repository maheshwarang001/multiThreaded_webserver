package com.swipe.authservice.entity;

import com.swipe.authservice.dao.UserDAO;
import com.swipe.authservice.model.User;
import com.swipe.authservice.repository.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserMysqlDb implements UserDAO {

    @Autowired
    private  UserMysqlRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Override
    public User userExists(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public void createUser(User user) {

        if(userExists(user.getEmail()) != null) {
            throw new IllegalArgumentException();
        }
        else {
            user.setPwd(passwordEncoder.encode(user.getPwd()));
            userRepository.save(user);
        }

    }

    @Override
    public UUID getUUIDByEmail(String username) {
        User u = userExists(username);
        return u.getUserId();
    }

    @Override
    public boolean userLogin(User user) {
        return false;
    }

    @Override
    public void deleteUser(UUID uuid) {
        userRepository.deleteUserByUserId(uuid);
    }

    @Override
    public void updatePwd(User user) {
        userRepository.save(user);
    }
}
