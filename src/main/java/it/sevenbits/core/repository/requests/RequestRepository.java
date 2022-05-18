package it.sevenbits.core.repository.requests;

import it.sevenbits.core.model.Request;

import java.sql.Timestamp;
import java.util.List;

/**
 * The interface Request repository.
 */
public interface RequestRepository {
    /**
     * Gets request by id.
     *
     * @param requestId the request id
     * @return the request by id
     */
    Request getRequestById(String requestId);
    List<Request> getRequestsByUserId(String userId, Timestamp time);
    List<Request> getAllRequests(Timestamp time);

    /**
     * Add request request.
     *
     * @param request the request
     * @return the request
     */
    Request addRequest(Request request);

    /**
     * Update request request.
     *
     * @param request the request
     * @return the request
     */
    Request updateRequest(Request request);
    void removeRequest(String requestId);
}
