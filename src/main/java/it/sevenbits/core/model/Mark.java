package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * The type Mark.
 */
public class Mark {
    @JsonProperty("markId")
    private final String markId;

    @JsonProperty("markTime")
    private final Timestamp markTime;

    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("confidence")
    private final float confidence;

    @JsonProperty("approved")
    private final boolean approved;

    /**
     * Instantiates a new Mark.
     *
     * @param markId       the mark id
     * @param markTime     the mark time
     * @param frameAddress the frame address
     * @param userId       the user id
     * @param confidence   the confidence
     * @param approved     the approved
     */
    @JsonCreator
    public Mark(String markId, Timestamp markTime, String frameAddress, String userId, float confidence, boolean approved) {
        this.markId = markId;
        this.markTime = markTime;
        this.frameAddress = frameAddress;
        this.userId = userId;
        this.confidence = confidence;
        this.approved = approved;
    }

    /**
     * Gets mark id.
     *
     * @return the mark id
     */
    public String getMarkId() {
        return markId;
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

    /**
     * Is approved boolean.
     *
     * @return the boolean
     */
    public boolean isApproved() {
        return approved;
    }
}
