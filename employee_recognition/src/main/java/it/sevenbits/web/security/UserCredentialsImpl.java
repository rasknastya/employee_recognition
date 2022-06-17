package it.sevenbits.web.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The type User credentials.
 */
public class UserCredentialsImpl implements UserCredentials {

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("roles")
    private final Set<String> roles;


    /**
     * Instantiates a new User credentials.
     *
     * @param userId the user id
     * @param email  the email
     * @param roles  the roles
     */
    @JsonCreator
    public UserCredentialsImpl(final String userId, final String email, final Collection<String> roles) {
        this.userId = userId;
        this.email = email;
        this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

}


