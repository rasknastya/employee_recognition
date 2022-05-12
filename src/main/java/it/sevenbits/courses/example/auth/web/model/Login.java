package it.sevenbits.courses.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to receive username and password.
 */
public class Login {

    private final String email;
    private final String password;

    @JsonCreator
    public Login(@JsonProperty("email") String email, @JsonProperty("password")String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
