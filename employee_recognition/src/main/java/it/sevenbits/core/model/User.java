package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type User.
 */
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

    /**
     * Instantiates a new User.
     *
     * @param userId    the user id
     * @param email     the email
     * @param fullName  the full name
     * @param roles     the roles
     * @param embedding the embedding
     * @param password  the password
     */
    public User(String userId, String email, String fullName, List<String> roles, BigDecimal[] embedding, String password) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
        this.embedding = embedding;
    }

    /**
     * Instantiates a new User.
     *
     * @param userId    the user id
     * @param email     the email
     * @param fullName  the full name
     * @param roles     the roles
     * @param embedding the embedding
     */
    @JsonCreator
    public User(String userId, String email, String fullName, List<String> roles, BigDecimal[] embedding) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = null;
        this.roles = roles;
        this.embedding = embedding;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
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

    /**
     * Get embedding big decimal [ ].
     *
     * @return the big decimal [ ]
     */
    public BigDecimal[] getEmbedding() {
        return embedding;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
