package it.sevenbits.web.controller;

import it.sevenbits.core.model.Mark;
import it.sevenbits.core.model.Request;
import it.sevenbits.core.service.requests.RequestService;
import it.sevenbits.web.security.AuthRoleRequired;
import it.sevenbits.web.security.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The type Request controller.
 */
@Controller
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    /**
     * Instantiates a new Request controller.
     *
     * @param requestService the request service
     */
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * Gets requests.
     *
     * @param time the time
     * @return the requests
     */
    @GetMapping("all")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<List<Request>> getRequests(@RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(requestService.getRequests(timestamp), HttpStatus.OK);
    }

    /**
     * Gets any user requests.
     *
     * @param userId the user id
     * @param time   the time
     * @return the any user requests
     */
    @GetMapping("/byuserid/{userId}")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<List<Request>> getAnyUserRequests(@PathVariable final String userId,
                                                         @RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(requestService.getRequests(userId, timestamp),
                HttpStatus.OK);
    }

    /**
     * Gets request.
     *
     * @param requestId the request id
     * @return the request
     */
    @GetMapping("/superior/{requestId}")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<Request> getRequest(@PathVariable final String requestId) {
        return Optional.ofNullable(requestService.superGetRequest(requestId))
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Approve request response entity.
     *
     * @param requestId the request id
     * @param approved  the approved
     * @return the response entity
     */
    @PostMapping("/superior/{requestId}")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<Mark> approveRequest(@PathVariable final String requestId,
                                                  @RequestParam final Boolean approved) {
        return new ResponseEntity<>(requestService.approveRequest(requestId, approved), HttpStatus.OK);
    }

    /**
     * Gets request.
     *
     * @param userCredentials the user credentials
     * @param requestId       the request id
     * @return the request
     */
    @GetMapping("/{requestId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Request> getRequest(final UserCredentials userCredentials,
                                                 @PathVariable final String requestId) {
        return new ResponseEntity<>(requestService.getRequest(userCredentials.getUserId(), requestId), HttpStatus.OK);
    }

    /**
     * Gets requests.
     *
     * @param userCredentials the user credentials
     * @param time            the time
     * @return the requests
     */
    @GetMapping("")
    @AuthRoleRequired("USER")
    public ResponseEntity<List<Request>> getRequests(final UserCredentials userCredentials,
                                                     @RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(requestService.getRequests(userCredentials.getUserId(), timestamp),
                HttpStatus.OK);
    }
}
