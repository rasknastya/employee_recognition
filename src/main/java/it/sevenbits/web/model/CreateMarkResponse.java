package it.sevenbits.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.core.model.Mark;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMarkResponse {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");

    @JsonProperty("requestId")
    private final String requestId;

    private final Timestamp requestTime;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("changedMark")
    private final Mark changedMark;

    @JsonProperty("commentary")
    private final String commentary;

    @JsonCreator
    public CreateMarkResponse(String requestId, Timestamp requestTime, String userId, Mark changedMark, String commentary) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.userId = userId;
        this.changedMark = changedMark;
        this.commentary = commentary;
    }

    public String getRequestId() {
        return requestId;
    }

    @JsonIgnore
    public Timestamp getRequestTime() {
        return requestTime;
    }

    public String getFormattedRequestTime() {
        return sdf.format(new Date(requestTime.getTime()));
    }

    public String getUserId() {
        return userId;
    }

    public Mark getChangedMark() {
        return changedMark;
    }

    public String getCommentary() {
        return commentary;
    }
}
