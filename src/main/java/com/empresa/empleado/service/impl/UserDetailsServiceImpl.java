package com.empresa.empleado.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // User Repository
        if ("admin".equals(username)) {
            return new User("admin", "$2a$10$dNS8b2Tf6sofRfGbjTHwMOSrOqpUfAKB59sB8qWK.YmcEXDudWsXK", Collections.emptyList());
            // password = "1234" encrypt by BCrypt
        }
        throw new UsernameNotFoundException("User not found");
    }
}