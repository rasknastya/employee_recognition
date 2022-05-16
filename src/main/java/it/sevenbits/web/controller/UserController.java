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
 * Controller to list users.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/embeddings")
    @AuthRoleRequired("CAMERAMODULE")
    public ResponseEntity<Map<String, BigDecimal[]>> getAllEmbeddings() {
        return new ResponseEntity<>(userService.getEmbeddings(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.getAllUsers());
    }

    @GetMapping(value = "/{username}")
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<User> getUserInfo(final @PathVariable("username") String username) {
        return Optional
                .ofNullable( userRepository.findUserByEmail(username) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping(value = "/register")
    @AuthRoleRequired("ADMIN")
    public @ResponseBody ResponseEntity registerUser(final RegisterUserRequest request) throws IOException, SQLException {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }
}
