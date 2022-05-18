package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.core.model.Mark;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Change mark response.
 */
public class ChangeMarkResponse {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    @JsonProperty("requestId")
    private final String requestId;

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

    @JsonIgnore
    /**
     * Gets request time.
     *
     * @return the request time
     */
    public Timestamp getRequestTime() {
        return requestTime;
    }

    public String getFormattedRequestTime() {
        return sdf.format(new Date(requestTime.getTime()));
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
