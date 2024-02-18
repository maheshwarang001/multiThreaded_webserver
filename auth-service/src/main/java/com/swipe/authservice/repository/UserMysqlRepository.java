package com.swipe.authservice.repository;

import com.swipe.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserMysqlRepository extends JpaRepository<User, UUID>  {

    User findByEmail(String email);

    void deleteUserByUserId(UUID uuid);

}
