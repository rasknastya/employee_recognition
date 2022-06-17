package it.sevenbits.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.jdbc.datasource.SmartDataSource;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The type Request.
 */
public class Request {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    @JsonProperty("requestId")
    private final String requestId;

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
    @JsonIgnore
    /**
     * Gets request time.
     *
     * @return the request time
     */
    public Timestamp getRequestTime() {
        return requestTime;
    }

    /**
     * Gets formatted request time.
     *
     * @return the formatted request time
     */
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

    /**
     * Gets fields.
     *
     * @return the fields
     */
    @JsonIgnore
    public List<String> getFields() {
        List<String> fields = new ArrayList<>();
        fields.add(requestId);
        fields.add(getFormattedRequestTime());
        fields.add(userId);
        fields.add(markId);
        fields.add(changedMarkId);
        fields.add(commentary);
        return fields;
    }
}
