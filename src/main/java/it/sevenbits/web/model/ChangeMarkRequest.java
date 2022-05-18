package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * The type Change mark request.
 */
public class ChangeMarkRequest {
    @JsonProperty("markTime")
    private final Timestamp markTime;

    @JsonProperty("frameAddress")
    private final String frameAddress;

    @JsonProperty("commentary")
    private final String commentary;

    /**
     * Instantiates a new Change mark request.
     *
     * @param markTime     the mark time
     * @param frameAddress the frame address
     * @param commentary   the commentary
     */
    @JsonCreator
    public ChangeMarkRequest(Timestamp markTime, String frameAddress, String commentary) {
        this.markTime = markTime;
        this.frameAddress = frameAddress;
        this.commentary = commentary;
    }

    /**
     * Gets commentary.
     *
     * @return the commentary
     */
    public String getCommentary() {
        return commentary;
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
}
