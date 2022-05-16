package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ChangeMarkRequest {
    @JsonProperty("markTime")
    private final Timestamp markTime;

    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("commentary")
    private final String commentary;

    @JsonCreator
    public ChangeMarkRequest(Timestamp markTime, String frameAddress, String commentary) {
        this.markTime = markTime;
        this.frameAddress = frameAddress;
        this.commentary = commentary;
    }

    public String getCommentary() {
        return commentary;
    }

    public Timestamp getMarkTime() {
        return markTime;
    }


    public String getFrameAddress() {
        return frameAddress;
    }
}
