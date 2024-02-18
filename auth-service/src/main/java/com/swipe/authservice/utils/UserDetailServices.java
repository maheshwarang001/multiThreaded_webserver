package com.swipe.authservice.utils;

import com.swipe.authservice.model.UserCredential;
import com.swipe.authservice.service.UserService;
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
