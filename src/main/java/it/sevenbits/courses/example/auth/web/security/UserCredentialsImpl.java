package it.sevenbits.courses.example.auth.web.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class UserCredentialsImpl implements UserCredentials {

    @JsonProperty("username")
    private final String username;

    @JsonProperty("roles")
    private final Set<String> roles;

    @JsonCreator
    public UserCredentialsImpl(String username, Collection<String> roles) {
        this.username = username;
        this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

}
