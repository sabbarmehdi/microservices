package me.sabbar.users.service;

import me.sabbar.users.Model.Item;
import me.sabbar.users.Model.ProfileRole;
import me.sabbar.users.Model.User;
import me.sabbar.users.dto.UserDTO;
import me.sabbar.users.dto.handler.AuthenticateHandlerRequest;
import me.sabbar.users.exception.*;
import me.sabbar.users.mapper.UserMapper;
import me.sabbar.users.repository.UserRepository;
import me.sabbar.users.security.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.*;

import static me.sabbar.users.constants.ColumnName.EMAIL;


@Service
@Log4j2
public class UserService{

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;
    @Autowired
    protected UserRepository userRepository;
    @Lazy
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private ItemsServiceClient itemsServiceClient;


    @Transactional
    public UserDTO findByEmail(String email) {
        if (email == null) {
            throw new NullValueException(EMAIL);
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
        return userMapper.mapToDTO(user);
    }

    @Transactional
    public String checkEmailNotExist(String email) {
        if (email == null) {
            throw new NullValueException(EMAIL);
        }

        boolean isPresent = userRepository.findByEmail(email).isPresent();

        if (isPresent) {
            throw new EmailAlreadyExisteException();
        }

        return "Email not exist in db";
    }


    @Transactional
    public UserDTO findDTOById(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        return userMapper.mapToDTO(user);
    }


    @Transactional
    public User findById(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
    }

    @Transactional
    public void createUser(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new CredentialAlreadyExistsException("Email");
        }

        ProfileRole profileRoles = ProfileRole.PROFILE_ROLE;

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        userDTO.setRoles(profileRoles);

        userRepository.save(userMapper.mapToEntityNoBackup(userDTO));
    }



    @Transactional
    public UserDTO authenticate(AuthenticateHandlerRequest user) {
        log.debug("authenticate:: email: "+user.getEmail());

        if (user == null) {
            throw new NullValueException("user");
        }
        if (user.getEmail() == null) {
            throw new NullValueException(EMAIL);
        }
        if (user.getPassword() == null) {
            throw new NullValueException("password");
        }
        String username = user.getEmail().toLowerCase();
        UserDTO userDto = findByEmail(username);

        if (!passwordEncoder.matches(user.getPassword(), userDto.getPassword())) {
            throw new WrongPasswordException(User.class);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));

        userDto.setToken(jwtTokenProvider.createToken(username, findByEmail(username).getRoles()));

        return userDto;
    }

    @Transactional
    public String deleteUser(Long id) {
        if (id == null) {
            throw new NullValueException("id");
        }
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        userRepository.delete(userToDelete);

        return "User is deleted";
    }


    @Transactional
    public UserDTO update(UserDTO userDto) {

        User user = userMapper.mapToEntityNoBackup(findDTOById(userDto.getId()));

        try {

            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setRoles(userDto.getRoles());

            return userMapper.mapToDTO(userRepository.save(user));

        } catch (Exception e) {
            throw new NotFoundException("Error while updating user : " + e);
        }

    }

    /**
     * Get authenticated user email
     * @return email
     */
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    /**
     * Get all items of authenticated user
     * using Rest Template
     * @return List of Item
     */
    public List<Item> findAllItemsById() {

        Long userId = userRepository.findByEmail(getUsername()).get().getId();
        String url = String.format(environment.getProperty("user.items.url"), userId);

        ResponseEntity<List<Item>> items = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Item>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
        List<Item> itemList= items.getBody();
        return itemList;
    }


    /**
     * Get all items of authenticated user
     * using Feign
     * @return List of Item
     */
    public List<Item> findAllItemById() {

        Long userId = userRepository.findByEmail(getUsername()).get().getId();
log.info("before request");
        List<Item> itemList= itemsServiceClient.getItems(userId);
        log.info("after request");
        return itemList;
    }


}

