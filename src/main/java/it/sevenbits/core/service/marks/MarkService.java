package it.sevenbits.core.service.marks;

import it.sevenbits.core.repository.requests.RequestRepository;
import it.sevenbits.core.model.Mark;
import it.sevenbits.core.model.Request;
import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.marks.MarkRepository;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.web.controller.exception.NotAvailableException;
import it.sevenbits.web.controller.exception.NotFoundException;
import it.sevenbits.web.model.ChangeMarkRequest;
import it.sevenbits.web.model.ChangeMarkResponse;
import it.sevenbits.web.model.CreateMarkResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);

    public MarkService(MarkRepository markRepository, RequestRepository requestRepository, UserRepository userRepository) {
        this.markRepository = markRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Mark getMark(String userId, String markId) {
        return validateUserMark(userId, markId);
    }

    public List<Mark> getUserMarks(String userId) {
        return markRepository.getMarksByUserId(userId);
    }

    public List<Mark> getAllMarks() {
        return markRepository.getAllMarks();
    }

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

    public CreateMarkResponse createMark(String userId, ChangeMarkRequest changeMarkRequest) {
        Mark newMark = new Mark(UUID.randomUUID().toString(), changeMarkRequest.getMarkTime(), changeMarkRequest.getFrameAddress(),
                userId, 0, false);
        newMark = markRepository.addMark(newMark);
        Request request = requestRepository.addRequest(new Request(UUID.randomUUID().toString(), new Timestamp(System.currentTimeMillis()),
                userId, null, newMark.getMarkId(), changeMarkRequest.getCommentary()));
        return new CreateMarkResponse(request.getRequestId(), request.getRequestTime(), userId,
                newMark, request.getCommentary());
    }

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
}
