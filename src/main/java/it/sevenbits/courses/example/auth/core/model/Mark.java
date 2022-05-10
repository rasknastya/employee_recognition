package it.sevenbits.courses.example.auth.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mark {
    @JsonProperty("markId")
    private final String markId;

    @JsonProperty("markTime")
    private final String markTime;

    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("confidence")
    private final float confidence;

    @JsonProperty("approved")
    private final boolean approved;

    @JsonCreator
    public Mark(String markId, String markTime, String frameAddress, String userId, float confidence, boolean approved) {
        this.markId = markId;
        this.markTime = markTime;
        this.frameAddress = frameAddress;
        this.userId = userId;
        this.confidence = confidence;
        this.approved = approved;
    }

    public String getMarkId() {
        return markId;
    }

    public String getMarkTime() {
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

    public boolean isApproved() {
        return approved;
    }
}
