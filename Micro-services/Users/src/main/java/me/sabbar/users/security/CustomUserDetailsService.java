package me.sabbar.users.security;


import me.sabbar.users.Model.User;
import me.sabbar.users.exception.NotFoundException;
import me.sabbar.users.repository.UserRepository;

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