package it.sevenbits.web.security;

import it.sevenbits.core.model.User;

public interface JwtTokenService {

    /**
     * Parses the token
     * @param token the token string to parse
     * @return authenticated data
     */
    UserCredentials parseToken(String token);

    /**
     * Creates new Token for user.
     * @param user contains User to be represented as token
     * @return signed token
     */
    String createToken(User user);

}
