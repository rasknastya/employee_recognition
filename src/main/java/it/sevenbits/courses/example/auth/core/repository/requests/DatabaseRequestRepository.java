package it.sevenbits.courses.example.auth.core.repository.requests;

import it.sevenbits.courses.example.auth.core.model.Request;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRequestRepository implements RequestRepository{
    private final JdbcOperations jdbcOperations;
    private final String REQUESTID = "request_id";
    private final String REQUESTTIME = "request_time";
    private final String USERID = "user_id";
    private final String MARKID = "mark_id";
    private final String CHANGEDMARKID = "changed_mark_id";
    private final String COMMENTARY = "commentary";

    public DatabaseRequestRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Request getRequestById(String requestId) {
        try {
            return jdbcOperations.queryForObject(
                    "SELECT request_time, user_id, mark_id, changed_mark_id, commentary" +
                            " FROM requests WHERE request_id = ?",
                    (resultSet, i) -> {
                        Timestamp requestTime = resultSet.getTimestamp(REQUESTTIME);
                        String userId = resultSet.getString(USERID);
                        String markId = resultSet.getString(MARKID);
                        String changedMarkId = resultSet.getString(CHANGEDMARKID);
                        String commentary = resultSet.getString(COMMENTARY);
                        return new Request(requestId, requestTime, userId, markId, changedMarkId, commentary);
                    },
                    requestId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Request> getRequestsByUserId(String userId) {
        List<Request> requests = new ArrayList<>();
        try {
            jdbcOperations.query(
                    "SELECT request_id, request_time, mark_id, changed_mark_id, commentary" +
                            " FROM requests WHERE user_id = ?",
                    (resultSet, i) -> {
                        String requestId = resultSet.getString(REQUESTID);
                        Timestamp requestTime = resultSet.getTimestamp(REQUESTTIME);
                        String markId = resultSet.getString(MARKID);
                        String changedMarkId = resultSet.getString(CHANGEDMARKID);
                        String commentary = resultSet.getString(COMMENTARY);
                        return requests.add(new Request(requestId, requestTime, userId, markId, changedMarkId, commentary));
                    },
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return requests;
        }
        return requests;
    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        try {
            jdbcOperations.query(
                    "SELECT request_id, request_time, user_id, mark_id, changed_mark_id, commentary" +
                            " FROM requests",
                    (resultSet, i) -> {
                        String requestId = resultSet.getString(REQUESTID);
                        Timestamp requestTime = resultSet.getTimestamp(REQUESTTIME);
                        String userId = resultSet.getString(USERID);
                        String markId = resultSet.getString(MARKID);
                        String changedMarkId = resultSet.getString(CHANGEDMARKID);
                        String commentary = resultSet.getString(COMMENTARY);
                        return requests.add(new Request(requestId, requestTime, userId, markId, changedMarkId, commentary));
                    });
        } catch (EmptyResultDataAccessException e) {
            return requests;
        }
        return requests;
    }

    @Override
    public Request addRequest(Request request){
        String requestId = request.getRequestId();
        Timestamp requestTime = request.getRequestTime();
        String userId = request.getUserId();
        String markId = request.getMarkId();
        String changedMarkId = request.getChangedMarkId();
        String commentary = request.getCommentary();
        jdbcOperations.update(
                "INSERT INTO requests (request_id, request_time, user_id, mark_id, changed_mark_id, commentary) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                requestId, requestTime, userId, markId, changedMarkId, commentary
        );
        return getRequestById(request.getRequestId());
    }

    @Override
    public Request updateRequest(Request request){
        String requestId = request.getRequestId();
        Timestamp requestTime = request.getRequestTime();
        String userId = request.getUserId();
        String markId = request.getMarkId();
        String changedMarkId = request.getChangedMarkId();
        String commentary = request.getCommentary();
        jdbcOperations.update(
                "UPDATE requests SET request_time = ?, user_id = ?, mark_id = ?, " +
                        "changed_mark_id = ?, commentary = ? WHERE request_id = ?",
                requestTime, userId, markId, changedMarkId, commentary, requestId
        );
        return getRequestById(request.getRequestId());
    }
}
