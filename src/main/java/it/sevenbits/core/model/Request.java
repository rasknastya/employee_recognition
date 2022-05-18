package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The type Request.
 */
public class Request {

    @JsonProperty("requestId")
    private final String requestId;

    @JsonProperty("requestTime")
    private final Timestamp requestTime;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("markId")
    private final String markId;

    @JsonProperty("changedMarkId")
    private final String changedMarkId;

    @JsonProperty("commentary")
    private final String commentary;

    /**
     * Instantiates a new Request.
     *
     * @param requestId     the request id
     * @param requestTime   the request time
     * @param userId        the user id
     * @param markId        the mark id
     * @param changedMarkId the changed mark id
     * @param commentary    the commentary
     */
    @JsonCreator
    public Request(String requestId, Timestamp requestTime, String userId, String markId, String changedMarkId, String commentary) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.userId = userId;
        this.markId = markId;
        this.changedMarkId = changedMarkId;
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
     * Gets mark id.
     *
     * @return the mark id
     */
    public String getMarkId() {
        return markId;
    }

    /**
     * Gets changed mark id.
     *
     * @return the changed mark id
     */
    public String getChangedMarkId() {
        return changedMarkId;
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
