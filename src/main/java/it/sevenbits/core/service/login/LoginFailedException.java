package it.sevenbits.core.service.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Login failed exception.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {

    /**
     * Instantiates a new Login failed exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Login failed exception.
     *
     * @param message the message
     */
    public LoginFailedException(String message) {
        super(message);
    }

}
