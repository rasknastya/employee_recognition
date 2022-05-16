package it.sevenbits.courses.example.auth.web.controller;

import it.sevenbits.courses.example.auth.core.model.Mark;
import it.sevenbits.courses.example.auth.core.service.marks.MarkService;
import it.sevenbits.courses.example.auth.web.model.AddMarkRequest;
import it.sevenbits.courses.example.auth.web.model.ChangeMarkRequest;
import it.sevenbits.courses.example.auth.web.model.ChangeMarkResponse;
import it.sevenbits.courses.example.auth.web.model.CreateMarkResponse;
import it.sevenbits.courses.example.auth.web.security.AuthRoleRequired;
import it.sevenbits.courses.example.auth.web.security.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Mark>> getAnyUserMarks(@PathVariable final String userId) {
        return new ResponseEntity<>(markService.getUserMarks(userId), HttpStatus.OK);
    }

    @GetMapping("")
    @AuthRoleRequired("SUPERIOR")
    public ResponseEntity<List<Mark>> getAllMarks() {
        return new ResponseEntity<>(markService.getAllMarks(), HttpStatus.OK);
    }

    @GetMapping("/{markId}")
    @AuthRoleRequired("USER")
    public ResponseEntity<Mark> getMark(final UserCredentials userCredentials,
                                        @PathVariable final String markId) {
        return new ResponseEntity<>(markService.getMark(userCredentials.getUserId(), markId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @AuthRoleRequired("USER")
    public ResponseEntity<List<Mark>> getUserMarks(final UserCredentials userCredentials) {
        return new ResponseEntity<>(markService.getUserMarks(userCredentials.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/{markId}/change")
    @AuthRoleRequired("USER")
    public ResponseEntity<ChangeMarkResponse> changeMark(final UserCredentials userCredentials,
                                                         @PathVariable final String markId,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.changeMark(userCredentials.getUserId(), markId,
                changeMarkRequest), HttpStatus.OK);
    }

    @PostMapping("/createmark")
    @AuthRoleRequired("USER")
    public ResponseEntity<CreateMarkResponse> createMark(final UserCredentials userCredentials,
                                                         final ChangeMarkRequest changeMarkRequest) {
        return new ResponseEntity<>(markService.createMark(userCredentials.getUserId(), changeMarkRequest), HttpStatus.OK);
    }
}
