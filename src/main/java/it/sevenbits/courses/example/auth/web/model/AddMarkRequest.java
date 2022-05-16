package it.sevenbits.courses.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class AddMarkRequest {
    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("confidence")
    private final float confidence;

    @JsonProperty("markTime")
    private final Timestamp markTime;

    @JsonCreator
    public AddMarkRequest(String frameAddress, String userId, float confidence, String markTime) {
        this.frameAddress = frameAddress;
        this.userId = userId;
        this.confidence = confidence;
        this.markTime = Timestamp.valueOf(markTime);
    }

    public Timestamp getMarkTime() {
        return markTime;
    }

    public String getFrameAddress() {
        return frameAddress;
    }

    public String getUserId() {
        return userId;
    }

    public float getConfidence() {
        return confidence;
    }
}
