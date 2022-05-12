package it.sevenbits.courses.example.auth.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Question not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
public class NotFoundException extends RuntimeException {
}
