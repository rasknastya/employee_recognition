package it.sevenbits.core.service.requests;

import it.sevenbits.core.model.Mark;
import it.sevenbits.core.model.Request;
import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.marks.MarkRepository;
import it.sevenbits.core.repository.requests.RequestRepository;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.service.marks.MarkService;
import it.sevenbits.web.controller.exception.NotAvailableException;
import it.sevenbits.web.controller.exception.NotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Request service.
 */
@Service
public class RequestService {
    private final Logger logger = LoggerFactory.getLogger(RequestService.class);
    private final RequestRepository requestRepository;
    private final MarkRepository markRepository;
    private final UserRepository userRepository;

    /**
     * Instantiates a new Request service.
     *
     * @param requestRepository the request repository
     * @param markRepository    the mark repository
     * @param userRepository    the user repository
     */
    public RequestService(RequestRepository requestRepository, MarkRepository markRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.markRepository = markRepository;
        this.userRepository = userRepository;
    }


    /**
     * Gets request.
     *
     * @param userId    the user id
     * @param requestId the request id
     * @return the request
     */
    public Request getRequest(String userId, String requestId) {
        return validateUserRequest(userId, requestId);
    }

    /**
     * Super get request request.
     *
     * @param requestId the request id
     * @return the request
     */
    public Request superGetRequest(String requestId) {
        return requestRepository.getRequestById(requestId);
    }

    /**
     * Gets requests.
     *
     * @param userId the user id
     * @param time   the time
     * @return the requests
     */
    public List<Request> getRequests(String userId, Timestamp time) {
        List<Request> requests = requestRepository.getRequestsByUserId(userId, time);
        exportRequests(requests);
        return requests;
    }

    /**
     * Gets requests.
     *
     * @param time the time
     * @return the requests
     */
    public List<Request> getRequests(Timestamp time) {
        List<Request> requests = requestRepository.getAllRequests(time);
        exportRequests(requests);
        return requests;
    }

    /**
     * Approve request mark.
     *
     * @param requestId the request id
     * @param approved  the approved
     * @return the mark
     */
    public Mark approveRequest(String requestId, Boolean approved) {
        Request request = Optional.ofNullable(requestRepository.getRequestById(requestId))
                .orElseThrow(NotFoundException::new);
        Mark mark = markRepository.updateMark(markRepository.getMarkById(
                request.getChangedMarkId()).setApproved(approved));

        if (approved) {
            markRepository.removeMark(request.getMarkId());
        }
        requestRepository.removeRequest(requestId);
        return mark;
    }

    private Request validateUserRequest(String userId, String requestId) {
        User user = userRepository.findUserById(userId);
        Request request = requestRepository.getRequestById(requestId);
        if (request == null) {
            logger.error("Request with id " + requestId + " was not found");
            throw new NotFoundException();
        }
        if (!Objects.equals(request.getUserId(), userId)) {
            logger.error("User " + user.getEmail() + " does not have access to the request");
            throw new NotAvailableException();
        }
        return request;
    }

    private void exportRequests(List<Request> requests) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm");
        XSSFSheet sheet = workbook.createSheet("Requests report " + sdf.format(new Date()));
        String[] columns = {"Request ID", "Request Time", "User ID", "Mark ID", "Changed Mark Id", "Commentary"};
        int rowNum = 0;
        int colNum = 0;
        Row row = sheet.createRow(rowNum++);
        for (String column : columns) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(column);
        }

        for (Request request : requests) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            List<String> fields = request.getFields();
            for (String field : fields) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(field);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("Requests report " + sdf.format(new Date()) +".xls");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Excel request report done");
    }
}
