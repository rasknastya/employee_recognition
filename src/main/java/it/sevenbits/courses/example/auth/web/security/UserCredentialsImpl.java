package it.sevenbits.courses.example.auth.web.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class UserCredentialsImpl implements UserCredentials {

    @JsonProperty("email")
    private final String email;

    @JsonProperty("roles")
    private final Set<String> roles;

    @JsonCreator
    public UserCredentialsImpl(String email, Collection<String> roles) {
        this.email = email;
        this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRoles() {
        return roles;
    }

}
