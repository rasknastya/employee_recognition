package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class User {

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("fullName")
    private final String fullName;

    @JsonProperty("roles")
    private List<String> roles;

    @JsonProperty("embedding")
    private final BigDecimal[] embedding;

    @JsonIgnore
    private final String password;

    public User(String userId, String email, String fullName, List<String> roles, BigDecimal[] embedding, String password) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
        this.embedding = embedding;
    }

    @JsonCreator
    public User(String userId, String email, String fullName, List<String> roles, BigDecimal[] embedding) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = null;
        this.roles = roles;
        this.embedding = embedding;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal[] getEmbedding() {
        return embedding;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
