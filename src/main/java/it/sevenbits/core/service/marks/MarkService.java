package it.sevenbits.core.service.marks;

import it.sevenbits.core.model.Mark;
import it.sevenbits.core.model.Request;
import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.marks.MarkRepository;
import it.sevenbits.core.repository.requests.RequestRepository;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.web.controller.exception.NotAvailableException;
import it.sevenbits.web.controller.exception.NotFoundException;
import it.sevenbits.web.model.ChangeMarkRequest;
import it.sevenbits.web.model.ChangeMarkResponse;
import it.sevenbits.web.model.CreateMarkResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Mark service.
 */
@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);

    /**
     * Instantiates a new Mark service.
     *
     * @param markRepository    the mark repository
     * @param requestRepository the request repository
     * @param userRepository    the user repository
     */
    public MarkService(MarkRepository markRepository, RequestRepository requestRepository, UserRepository userRepository) {
        this.markRepository = markRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    /**
     * Gets mark.
     *
     * @param userId the user id
     * @param markId the mark id
     * @return the mark
     */
    public Mark getMark(String userId, String markId) {
        return validateUserMark(userId, markId);
    }

    public Mark getMarkSuperior(String markId) {
        return markRepository.getMarkById(markId);
    }

    public List<Mark> getUserMarks(String userId, Timestamp time) {
        List<Mark> marks = markRepository.getMarksByUserId(userId, time);
        exportMarks(marks);
        return marks;
    }

    public List<Mark> getAllMarks(Timestamp time) {
        List<Mark> marks = markRepository.getAllMarks(time);
        exportMarks(marks);
        return marks;
    }

    /**
     * Change mark change mark response.
     *
     * @param userId            the user id
     * @param markId            the mark id
     * @param changeMarkRequest the change mark request
     * @return the change mark response
     */
    public ChangeMarkResponse changeMark(String userId, String markId, ChangeMarkRequest changeMarkRequest) {
        Mark mark = validateUserMark(userId, markId);
        String newFrameAddress;
        float confidence;
        if (mark.getFrameAddress().equals(changeMarkRequest.getFrameAddress())) {
            newFrameAddress = mark.getFrameAddress();
            confidence = mark.getConfidence();
        } else {
            newFrameAddress = changeMarkRequest.getFrameAddress();
            confidence = 0;
        }
        Mark newMark = new Mark(UUID.randomUUID().toString(), changeMarkRequest.getMarkTime(), newFrameAddress,
                userId, confidence, false);
        newMark = markRepository.addMark(newMark);
        Request request = requestRepository.addRequest(new Request(UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()),
                userId, markId, newMark.getMarkId(), changeMarkRequest.getCommentary()));
        return new ChangeMarkResponse(request.getRequestId(), request.getRequestTime(), userId,
                mark, newMark, request.getCommentary());
    }

    /**
     * Create mark create mark response.
     *
     * @param userId            the user id
     * @param changeMarkRequest the change mark request
     * @return the create mark response
     */
    public CreateMarkResponse createMark(String userId, ChangeMarkRequest changeMarkRequest) {
        Mark newMark = new Mark(UUID.randomUUID().toString(), changeMarkRequest.getMarkTime(), changeMarkRequest.getFrameAddress(),
                userId, 0, false);
        newMark = markRepository.addMark(newMark);
        Request request = requestRepository.addRequest(new Request(UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()),
                userId, null, newMark.getMarkId(), changeMarkRequest.getCommentary()));
        return new CreateMarkResponse(request.getRequestId(), request.getRequestTime(), userId,
                newMark, request.getCommentary());
    }

    /**
     * Add mark mark.
     *
     * @param frameAddress the frame address
     * @param userId       the user id
     * @param confidence   the confidence
     * @param markTime     the mark time
     * @return the mark
     */
    public Mark addMark(String frameAddress, String userId, float confidence, Timestamp markTime) {
        return markRepository.addMark(new Mark(UUID.randomUUID().toString(), markTime,
                frameAddress, userId, confidence, true));
    }

    private Mark validateUserMark(String userId, String markId) {
        User user = userRepository.findUserById(userId);
        Mark mark = markRepository.getMarkById(markId);
        if (mark == null) {
            logger.error("Mark with id " + markId + " was not found");
            throw new NotFoundException();
        }
        if (!Objects.equals(mark.getUserId(), userId)) {
            logger.error("User " + user.getEmail() + " does not have access to the mark");
            throw new NotAvailableException();
        }
        return mark;
    }

    private void exportMarks(List<Mark> marks) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm");
        XSSFSheet sheet = workbook.createSheet("Marks report " + sdf.format(new Date()));
        String[] columns = {"Mark ID", "Mark Time", "Frame Address", "User ID", "Confidence", "Approved"};
        int rowNum = 0;

        int colNum = 0;
        Row row = sheet.createRow(rowNum++);
        for (String column : columns) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(column);
        }

        for (Mark mark : marks) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            List<String> fields = mark.getFields();
            for (String field : fields) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(field);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("Marks report " + sdf.format(new Date()) +".xls");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Excel mark report done");
    }
}
