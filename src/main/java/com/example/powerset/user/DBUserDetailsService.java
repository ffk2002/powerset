package com.example.powerset.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    private final UserDetailsMapper mapper;

    public DBUserDetailsService(UserRepository repo, UserDetailsMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PowersetUser> u = repo.findByUsername(username);
        return u.map(mapper::toUserDetails).orElse(null);
    }
}
