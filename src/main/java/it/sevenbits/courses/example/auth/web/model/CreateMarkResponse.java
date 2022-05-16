package it.sevenbits.courses.example.auth.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.courses.example.auth.core.model.Mark;

import java.sql.Timestamp;

public class CreateMarkResponse {

    @JsonProperty("requestId")
    private final String requestId;

    @JsonProperty("requestTime")
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

    public Timestamp getRequestTime() {
        return requestTime;
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
