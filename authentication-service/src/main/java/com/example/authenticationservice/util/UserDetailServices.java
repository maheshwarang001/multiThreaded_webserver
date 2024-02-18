package com.example.authenticationservice.util;


import com.example.authenticationservice.model.UserCredential;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailServices implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserCredential> data = Optional.ofNullable(userService.userCredentialExists(username));
        return data.map(UserDetailObject::new).orElseThrow(()->
                new UsernameNotFoundException("User Doesn't exists"));
    }
}
