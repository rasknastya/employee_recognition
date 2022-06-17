package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Mark.
 */
public class Mark {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    @JsonProperty("markId")
    private final String markId;

    private final Timestamp markTime;

    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("confidence")
    private final float confidence;

    @JsonProperty("approved")
    private boolean approved;

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
    @JsonIgnore
    /**
     * Gets mark time.
     *
     * @return the mark time
     */
    public Timestamp getMarkTime() {
        return markTime;
    }

    /**
     * Gets formatted mark time.
     *
     * @return the formatted mark time
     */
    public String getFormattedMarkTime() {
        return sdf.format(new Date(markTime.getTime()));
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

    /**
     * Sets approved.
     *
     * @param approved the approved
     * @return the approved
     */
    public Mark setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    /**
     * Gets fields.
     *
     * @return the fields
     */
    @JsonIgnore
    public List<String> getFields() {
        List<String> fields = new ArrayList<>();
        fields.add(markId);
        fields.add(getFormattedMarkTime());
        fields.add(frameAddress);
        fields.add(userId);
        fields.add(String.valueOf(confidence));
        fields.add(String.valueOf(approved));
        return fields;
    }
}
