package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @JsonCreator
    public Mark(String markId, Timestamp markTime, String frameAddress, String userId, float confidence, boolean approved) {
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

    @JsonIgnore
    public Timestamp getMarkTime() {
        return markTime;
    }

    public String getFormattedMarkTime() {
        return sdf.format(new Date(markTime.getTime()));
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

    public Mark setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

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
