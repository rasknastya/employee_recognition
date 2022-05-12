package it.sevenbits.courses.example.auth.core.security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder implements PasswordEncoder {

    public boolean matches(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
