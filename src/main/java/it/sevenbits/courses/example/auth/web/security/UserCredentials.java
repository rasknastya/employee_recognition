package it.sevenbits.courses.example.auth.web.security;

import java.util.Set;

public interface UserCredentials {

    String getEmail();
    Set<String> getRoles();
    String getUserId();

}
