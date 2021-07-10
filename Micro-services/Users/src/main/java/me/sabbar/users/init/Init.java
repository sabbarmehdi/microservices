package me.sabbar.users.init;

import me.sabbar.users.Model.ProfileRole;
import me.sabbar.users.Model.User;
import me.sabbar.users.dto.UserDTO;
import me.sabbar.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
