package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to send token.
 */
public class Token {

    private final String token;

    /**
     * Instantiates a new Token.
     *
     * @param token the token
     */
    @JsonCreator
    public Token(@JsonProperty("token") String token) {
        this.token = token;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

}
