package it.sevenbits.web.security;

import java.util.Set;

/**
 * The interface User credentials.
 */
public interface UserCredentials {

    /**
     * Gets email.
     *
     * @return the email
     */
    String getEmail();

    /**
     * Gets roles.
     *
     * @return the roles
     */
    Set<String> getRoles();

    /**
     * Gets user id.
     *
     * @return the user id
     */
    String getUserId();

}
