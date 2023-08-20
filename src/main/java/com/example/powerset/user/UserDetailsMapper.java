package com.example.powerset.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserDetailsMapper {

    UserDetails toUserDetails(PowersetUser u) {

        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .build();
    }
}