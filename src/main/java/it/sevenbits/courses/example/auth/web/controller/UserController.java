package it.sevenbits.courses.example.auth.web.controller;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import it.sevenbits.courses.example.auth.web.security.AuthRoleRequired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Controller to list users.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
