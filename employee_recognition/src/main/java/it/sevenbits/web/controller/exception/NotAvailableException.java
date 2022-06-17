package it.sevenbits.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not available object exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Object not available")
public class NotAvailableException extends RuntimeException {
}
