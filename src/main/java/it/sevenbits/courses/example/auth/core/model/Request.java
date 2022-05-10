package it.sevenbits.courses.example.auth.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    @JsonProperty("requestId")
    private final String requestId;

    @JsonProperty("requestTime")
    private final String requestTime;

    @JsonProperty("userId")
    private final String userId;

    @JsonProperty("markId")
    private final String markId;

    @JsonProperty("changedMarkId")
    private final String changedMarkId;

    @JsonProperty("commentary")
    private final String commentary;

    @JsonCreator
    public Request(String requestId, String requestTime, String userId, String markId, String changedMarkId, String commentary) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.userId = userId;
        this.markId = markId;
        this.changedMarkId = changedMarkId;
        this.commentary = commentary;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getMarkId() {
        return markId;
    }

    public String getChangedMarkId() {
        return changedMarkId;
    }

    public String getCommentary() {
        return commentary;
    }
}