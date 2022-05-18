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

    /**
     * Gets requests by user id.
     *
     * @param userId the user id
     * @param time   the time
     * @return the requests by user id
     */
    List<Request> getRequestsByUserId(String userId, Timestamp time);

    /**
     * Gets all requests.
     *
     * @param time the time
     * @return the all requests
     */
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

    /**
     * Remove request.
     *
     * @param requestId the request id
     */
    void removeRequest(String requestId);
}
