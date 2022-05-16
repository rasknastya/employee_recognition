package it.sevenbits.web.controller.exception;

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
    public LoginFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Login failed exception.
     *
     * @param message the message
     */
    public LoginFailedException(final String message) {
        super(message);
    }
}
