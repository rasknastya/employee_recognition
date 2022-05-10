package it.sevenbits.courses.example.auth.web.controller;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UsersRepository;
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
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersRepository.findAll());
    }

    @GetMapping(value = "/{username}")
    @ResponseBody
    @AuthRoleRequired("ADMIN")
    public ResponseEntity<User> getUserInfo(final @PathVariable("username") String username) {
        return Optional
                .ofNullable( usersRepository.findByUserName(username) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
