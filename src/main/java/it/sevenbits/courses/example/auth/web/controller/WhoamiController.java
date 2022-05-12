package it.sevenbits.courses.example.auth.web.controller;

import it.sevenbits.courses.example.auth.web.security.AuthRoleRequired;
import it.sevenbits.courses.example.auth.web.security.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to display the current user.
 */
@Controller
@RequestMapping("/whoami")
public class WhoamiController {

    @GetMapping
    @ResponseBody
    @AuthRoleRequired("USER")
    public ResponseEntity<UserCredentials> get(
        UserCredentials userCredentials
    ) {
        return ResponseEntity.ok(userCredentials);
    }

}
