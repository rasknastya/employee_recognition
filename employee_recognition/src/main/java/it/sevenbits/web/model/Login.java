package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to receive username and password.
 */
public class Login {

    private final String email;
    private final String password;

    /**
     * Instantiates a new Login.
     *
     * @param email    the email
     * @param password the password
     */
    @JsonCreator
    public Login(@JsonProperty("email") String email, @JsonProperty("password")String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
