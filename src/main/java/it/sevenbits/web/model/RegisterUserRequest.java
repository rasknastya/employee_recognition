package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class RegisterUserRequest {
    @JsonProperty("email")
    private final String email;

    @JsonProperty("fullName")
    private final String fullName;

    @JsonProperty("password")
    private final String password;

    @JsonProperty("files")
    private final MultipartFile[] files;

    @JsonCreator
    public RegisterUserRequest(String email, String fullName, String password, MultipartFile[] files) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.files = files;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public MultipartFile[] getFiles() {
        return files;
    }
}
