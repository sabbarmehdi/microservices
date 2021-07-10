package com.peaqock.users.init;

import com.peaqock.users.Model.ProfileRole;
import com.peaqock.users.Model.User;
import com.peaqock.users.dto.UserDTO;
import com.peaqock.users.repository.UserRepository;
import com.peaqock.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init {

    @Value("${init.data}")
    private boolean init;

    private final UserService userService;
    protected User user_1 = null;

    @PostConstruct
    public void init() {
        if (!init) {
            return;
        }
        userService.createUser(user_1());
    }

        private UserDTO user_1() {
        ProfileRole profileRoles = ProfileRole.PROFILE_ROLE;

        return UserDTO.builder()
                .firstName("Mehdi")
                .lastName("Sabbar")
                .email("user1@gmail.com")
                .password("Admin@123")
                .roles(profileRoles)
                .build();
    }

}
