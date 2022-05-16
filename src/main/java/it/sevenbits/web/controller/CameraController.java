package it.sevenbits.web.controller;

import it.sevenbits.core.service.marks.MarkService;
import it.sevenbits.web.model.AddMarkRequest;
import it.sevenbits.web.security.AuthRoleRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/camera")
public class CameraController {
    private final MarkService markService;

    public CameraController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping("")
    @AuthRoleRequired("CAMERAMODULE")
    public ResponseEntity<HttpStatus> addMark(final AddMarkRequest addMarkRequest) {
        markService.addMark(addMarkRequest.getFrameAddress(), addMarkRequest.getUserId(),
                addMarkRequest.getConfidence(), addMarkRequest.getMarkTime());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
