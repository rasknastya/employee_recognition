package it.sevenbits.core.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type B crypt password encoder.
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    public boolean matches(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

}
