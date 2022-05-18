package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Register user request.
 */
public class RegisterUserRequest {
    @JsonProperty("email")
    private final String email;

    @JsonProperty("fullName")
    private final String fullName;

    @JsonProperty("password")
    private final String password;

    @JsonProperty("files")
    private final MultipartFile[] files;

    /**
     * Instantiates a new Register user request.
     *
     * @param email    the email
     * @param fullName the full name
     * @param password the password
     * @param files    the files
     */
    @JsonCreator
    public RegisterUserRequest(String email, String fullName, String password, MultipartFile[] files) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.files = files;
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
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
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
     * Get files multipart file [ ].
     *
     * @return the multipart file [ ]
     */
    public MultipartFile[] getFiles() {
        return files;
    }
}
