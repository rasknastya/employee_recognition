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

    @JsonCreator
    public Request(String requestId, Timestamp requestTime, String userId, String markId, String changedMarkId, String commentary) {
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

    public String getMarkId() {
        return markId;
    }

    public String getChangedMarkId() {
        return changedMarkId;
    }

    public String getCommentary() {
        return commentary;
    }

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
