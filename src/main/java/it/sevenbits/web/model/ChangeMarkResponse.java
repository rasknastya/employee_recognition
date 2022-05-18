package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.core.model.Mark;

import java.sql.Timestamp;

/**
 * The type Change mark response.
 */
public class ChangeMarkResponse {

    @JsonProperty("requestId")
    private final String requestId;

    @JsonProperty("requestTime")
    private final Timestamp requestTime;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("mark")
    private final Mark mark;

    @JsonProperty("changedMark")
    private final Mark changedMark;

    @JsonProperty("commentary")
    private final String commentary;

    /**
     * Instantiates a new Change mark response.
     *
     * @param requestId   the request id
     * @param requestTime the request time
     * @param userId      the user id
     * @param mark        the mark
     * @param changedMark the changed mark
     * @param commentary  the commentary
     */
    @JsonCreator
    public ChangeMarkResponse(String requestId, Timestamp requestTime, String userId, Mark mark, Mark changedMark, String commentary) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.userId = userId;
        this.mark = mark;
        this.changedMark = changedMark;
        this.commentary = commentary;
    }

    /**
     * Gets request id.
     *
     * @return the request id
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Gets request time.
     *
     * @return the request time
     */
    public Timestamp getRequestTime() {
        return requestTime;
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
     * Gets mark.
     *
     * @return the mark
     */
    public Mark getMark() {
        return mark;
    }

    /**
     * Gets changed mark.
     *
     * @return the changed mark
     */
    public Mark getChangedMark() {
        return changedMark;
    }

    /**
     * Gets commentary.
     *
     * @return the commentary
     */
    public String getCommentary() {
        return commentary;
    }
}
