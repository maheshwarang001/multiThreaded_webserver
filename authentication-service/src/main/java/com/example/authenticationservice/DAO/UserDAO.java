package com.example.authenticationservice.DAO;



import com.example.authenticationservice.model.User;

import java.util.UUID;

public interface UserDAO {

    User userExists(String username);
    void createUser(User username);

    UUID getUUIDByEmail(String username);

    boolean userLogin(User user);

    void deleteUser(UUID uuid);

    void updatePwd(User user);

}
