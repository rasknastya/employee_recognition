package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * The type Add mark request.
 */
public class AddMarkRequest {
    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("confidence")
    private final float confidence;

    @JsonProperty("markTime")
    private final Timestamp markTime;

    /**
     * Instantiates a new Add mark request.
     *
     * @param frameAddress the frame address
     * @param userId       the user id
     * @param confidence   the confidence
     * @param markTime     the mark time
     */
    @JsonCreator
    public AddMarkRequest(String frameAddress, String userId, float confidence, String markTime) {
        this.frameAddress = frameAddress;
        this.userId = userId;
        this.confidence = confidence;
        this.markTime = Timestamp.valueOf(markTime);
    }

    /**
     * Gets mark time.
     *
     * @return the mark time
     */
    public Timestamp getMarkTime() {
        return markTime;
    }

    /**
     * Gets frame address.
     *
     * @return the frame address
     */
    public String getFrameAddress() {
        return frameAddress;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets confidence.
     *
     * @return the confidence
     */
    public float getConfidence() {
        return confidence;
    }
}
