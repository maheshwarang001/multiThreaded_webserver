package com.example.authenticationservice.entity;

import com.example.authenticationservice.DAO.UserDAO;
import com.example.authenticationservice.model.User;
import com.example.authenticationservice.repository.UserMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserMysqlDb implements UserDAO {

    @Autowired
    private UserMysqlRepository userRepository;

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
