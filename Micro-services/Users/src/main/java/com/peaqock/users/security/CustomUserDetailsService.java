package com.peaqock.users.security;


import com.peaqock.users.Model.User;
import com.peaqock.users.exception.NotFoundException;
import com.peaqock.users.repository.UserRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository users) {
        this.userRepository = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()
                -> new NotFoundException(User.class, username));
    }
}