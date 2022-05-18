package it.sevenbits.web.controller;

import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.service.user.UserService;
import it.sevenbits.web.model.RegisterUserRequest;
import it.sevenbits.web.security.AuthRoleRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller to users.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userRepository the user repository
     * @param userService    the user service
     */
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Gets all embeddings.
     *
     * @return the all embeddings
     */
    @GetMapping("/embeddings")
    @AuthRoleRequired("CAMERAMODULE")
    public ResponseEntity<Map<String, BigDecimal[]>> getAllEmbeddings() {
        return new ResponseEntity<>(userService.getEmbeddings(), HttpStatus.OK);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.getAllUsers());
    }

    /**
     * Gets user info.
     *
     * @param username the username
     * @return the user info
     */
    @GetMapping(value = "/byemail/{username}")
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<User> getUserInfoByEmail(final @PathVariable("username") String username) {
        return Optional
                .ofNullable( userRepository.findUserByEmail(username) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    /**
     * Gets user info.
     *
     * @param userId the user id
     * @return the user info
     */
    @GetMapping(value = "/{userId}")
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<User> getUserInfo(final @PathVariable("userId") String userId) {
        return Optional
                .ofNullable( userRepository.findUserById(userId) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    /**
     * Register user response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    @PostMapping(value = "/register")
    @AuthRoleRequired("ADMIN")
    public @ResponseBody ResponseEntity registerUser(final RegisterUserRequest request) throws IOException, SQLException {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }
}
