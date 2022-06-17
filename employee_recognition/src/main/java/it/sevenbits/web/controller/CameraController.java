package it.sevenbits.web.controller;

import it.sevenbits.core.service.marks.MarkService;
import it.sevenbits.web.model.AddMarkRequest;
import it.sevenbits.web.security.AuthRoleRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The type Camera controller.
 */
@RestController
@RequestMapping("/camera")
public class CameraController {
    private final MarkService markService;

    /**
     * Instantiates a new Camera controller.
     *
     * @param markService the mark service
     */
    public CameraController(MarkService markService) {
        this.markService = markService;
    }

    /**
     * Add mark response entity.
     *
     * @param addMarkRequest the add mark request
     * @return the response entity
     */
    @PostMapping("")
    @AuthRoleRequired("CAMERAMODULE")
    public ResponseEntity<HttpStatus> addMark(final AddMarkRequest addMarkRequest) {
        markService.addMark(addMarkRequest.getFrameAddress(), addMarkRequest.getUserId(),
                addMarkRequest.getConfidence(), addMarkRequest.getMarkTime());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
