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

@RestController
@RequestMapping("/marks")
public class MarkController {
    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

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
    @GetMapping("/superior/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Mark> getMark(@PathVariable final String markId) {
        return new ResponseEntity<>(markService.getMarkSuperior(markId), HttpStatus.OK);
    }

    @GetMapping("/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Mark> getMark(final UserCredentials userCredentials,
                                        @PathVariable final String markId) {
        return new ResponseEntity<>(markService.getMark(userCredentials.getUserId(), markId), HttpStatus.OK);
    }

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

    @PostMapping("/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<ChangeMarkResponse> changeMark(final UserCredentials userCredentials,
                                                         @PathVariable final String markId,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.changeMark(userCredentials.getUserId(), markId,
                changeMarkRequest), HttpStatus.OK);
    }

    @PostMapping("/")
    @AuthRoleRequired("USER")
    public ResponseEntity<CreateMarkResponse> createMark(final UserCredentials userCredentials,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.createMark(userCredentials.getUserId(), changeMarkRequest), HttpStatus.OK);
    }
}
