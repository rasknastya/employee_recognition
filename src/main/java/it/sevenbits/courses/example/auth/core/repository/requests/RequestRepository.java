package it.sevenbits.courses.example.auth.core.repository.requests;

import it.sevenbits.courses.example.auth.core.model.Request;

import java.util.List;

public interface RequestRepository {
    Request getRequestById(String requestId);
    List<Request> getRequestsByUserId(String userId);
    List<Request> getAllRequests();
    Request addRequest(Request request);
    Request updateRequest(Request request);
}
