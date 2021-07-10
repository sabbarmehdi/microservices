package me.sabbar.users.controller;

import me.sabbar.users.Model.Item;
import me.sabbar.users.dto.UserDTO;
import me.sabbar.users.dto.handler.AuthenticateHandlerRequest;
import me.sabbar.users.security.JwtTokenProvider;
import me.sabbar.users.service.UserService;
import me.sabbar.users.constants.PathsNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(PathsNames.USER)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findDTOById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<UserDTO> authenticate(
            @RequestBody AuthenticateHandlerRequest user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.authenticate(user));
    }

    @DeleteMapping(value = "/{id}")
    //@PreAuthorize("hasAnyRole(ROLE_ADMIN)")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }


    @GetMapping(value = "/getAuthenticatedUser")
    public String getAuthenticatedUser() {
        return userService.getUsername();
    }

    @GetMapping(value = "/check")
    public String check(){
        return "token"+ env.getProperty("token.secret");
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<List<Item>>(userService.findAllItemsById(), HttpStatus.OK);
    }

    @GetMapping(value = "/itemsByFeign")
    public ResponseEntity<List<Item>> getAllItemsByFeig(){
        return new ResponseEntity<List<Item>>(userService.findAllItemById(), HttpStatus.OK);
    }
}
