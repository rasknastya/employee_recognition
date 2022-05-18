package it.sevenbits.core.repository.requests;

import it.sevenbits.core.model.Request;

import java.sql.Timestamp;
import java.util.List;

public interface RequestRepository {
    Request getRequestById(String requestId);
    List<Request> getRequestsByUserId(String userId, Timestamp time);
    List<Request> getAllRequests(Timestamp time);
    Request addRequest(Request request);
    Request updateRequest(Request request);
    void removeRequest(String requestId);
}
