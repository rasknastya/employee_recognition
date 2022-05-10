package it.sevenbits.courses.example.auth.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Forbidden exception.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    /**
     * Instantiates a new Forbidden exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ForbiddenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Forbidden exception.
     *
     * @param message the message
     */
    public ForbiddenException(final String message) {
        super(message);
    }
}
