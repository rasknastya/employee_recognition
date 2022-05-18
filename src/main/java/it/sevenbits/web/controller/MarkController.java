package it.sevenbits.web.controller;

import it.sevenbits.core.model.Mark;
import it.sevenbits.core.service.marks.MarkService;
import it.sevenbits.web.model.ChangeMarkRequest;
import it.sevenbits.web.model.ChangeMarkResponse;
import it.sevenbits.web.model.CreateMarkResponse;
import it.sevenbits.web.security.AuthRoleRequired;
import it.sevenbits.web.security.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * The type Mark controller.
 */
@RestController
@RequestMapping("/marks")
public class MarkController {
    private final MarkService markService;

    /**
     * Instantiates a new Mark controller.
     *
     * @param markService the mark service
     */
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    /**
     * Gets any user marks.
     *
     * @param userId the user id
     * @param time   the time
     * @return the any user marks
     */
    @GetMapping("/byuserid/{userId}")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<List<Mark>> getAnyUserMarks(@PathVariable final String userId,
                                                      @RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(markService.getUserMarks(userId, timestamp), HttpStatus.OK);
    }

    /**
     * Gets all marks.
     *
     * @param time the time
     * @return the all marks
     */
    @GetMapping("/all")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<List<Mark>> getAllMarks(@RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(markService.getAllMarks(timestamp), HttpStatus.OK);
    }

    /**
     * Gets mark.
     *
     * @param markId the mark id
     * @return the mark
     */
    @GetMapping("/superior/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Mark> getMark(@PathVariable final String markId) {
        return new ResponseEntity<>(markService.getMarkSuperior(markId), HttpStatus.OK);
    }

    /**
     * Gets mark.
     *
     * @param userCredentials the user credentials
     * @param markId          the mark id
     * @return the mark
     */
    @GetMapping("/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Mark> getMark(final UserCredentials userCredentials,
                                        @PathVariable final String markId) {
        return new ResponseEntity<>(markService.getMark(userCredentials.getUserId(), markId), HttpStatus.OK);
    }

    /**
     * Gets user marks.
     *
     * @param userCredentials the user credentials
     * @param time            the time
     * @return the user marks
     */
    @GetMapping("")
    @AuthRoleRequired("USER")
    public ResponseEntity<List<Mark>> getUserMarks(final UserCredentials userCredentials,
                                                   @RequestParam final String time) {
        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(time);
        } catch (IllegalArgumentException e) {
            timestamp = null;
        }
        return new ResponseEntity<>(markService.getUserMarks(userCredentials.getUserId(), timestamp),
                HttpStatus.OK);
    }

    /**
     * Change mark response entity.
     *
     * @param userCredentials   the user credentials
     * @param markId            the mark id
     * @param changeMarkRequest the change mark request
     * @return the response entity
     */
    @PostMapping("/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<ChangeMarkResponse> changeMark(final UserCredentials userCredentials,
                                                         @PathVariable final String markId,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.changeMark(userCredentials.getUserId(), markId,
                changeMarkRequest), HttpStatus.OK);
    }

    /**
     * Create mark response entity.
     *
     * @param userCredentials   the user credentials
     * @param changeMarkRequest the change mark request
     * @return the response entity
     */
    @PostMapping("/")
    @AuthRoleRequired("USER")
    public ResponseEntity<CreateMarkResponse> createMark(final UserCredentials userCredentials,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.createMark(userCredentials.getUserId(), changeMarkRequest), HttpStatus.OK);
    }
}
