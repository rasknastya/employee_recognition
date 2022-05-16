package it.sevenbits.courses.example.auth.core.repository.marks;

import it.sevenbits.courses.example.auth.core.model.Mark;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMarkRepository implements MarkRepository{
    private final JdbcOperations jdbcOperations;
    private final String MARKID = "mark_id";
    private final String MARKTIME = "mark_time";
    private final String FRAMEADDRESS = "frame_address";
    private final String USERID = "user_id";
    private final String CONFIDENCE = "confidence";
    private final String APPROVED = "approved";


    public DatabaseMarkRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Mark getMarkById(String markId) {
        try {
            return jdbcOperations.queryForObject(
                    "SELECT mark_time, frame_address, user_id, confidence, approved" +
                            " FROM marks WHERE mark_id = ?",
                    (resultSet, i) -> {
                        Timestamp markTime = resultSet.getTimestamp(MARKTIME);
                        String frameAddress = resultSet.getString(FRAMEADDRESS);
                        String userId = resultSet.getString(USERID);
                        float confidence = resultSet.getFloat(CONFIDENCE);
                        boolean approved = resultSet.getBoolean(APPROVED);
                        return new Mark(markId, markTime, frameAddress, userId, confidence, approved);
                    },
                    markId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Mark> getMarksByUserId(String userId) {
        List<Mark> marks = new ArrayList<>();
        try {
             jdbcOperations.query(
                    "SELECT mark_id, mark_time, frame_address, confidence, approved" +
                            " FROM marks WHERE user_id = ?",
                    (resultSet, i) -> {
                        String id = resultSet.getString(MARKID);
                        Timestamp markTime = resultSet.getTimestamp(MARKTIME);
                        String frameAddress = resultSet.getString(FRAMEADDRESS);
                        float confidence = resultSet.getFloat(CONFIDENCE);
                        boolean approved = resultSet.getBoolean(APPROVED);
                        return marks.add(new Mark(id, markTime, frameAddress, userId, confidence, approved));
                    },
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return marks;
        }
        return marks;
    }

    public List<Mark> getAllMarks() {
        List<Mark> marks = new ArrayList<>();
        boolean approved = true;
        try {
            jdbcOperations.query(
                    "SELECT mark_id, mark_time, frame_address, user_id, confidence" +
                            " FROM marks WHERE user_id = ? AND approved =",
                    (resultSet, i) -> {
                        String id = resultSet.getString(MARKID);
                        Timestamp markTime = resultSet.getTimestamp(MARKTIME);
                        String frameAddress = resultSet.getString(FRAMEADDRESS);
                        String userId = resultSet.getString(USERID);
                        float confidence = resultSet.getFloat(CONFIDENCE);
                        return marks.add(new Mark(id, markTime, frameAddress, userId, confidence, approved));
                    },
                    approved);
        } catch (EmptyResultDataAccessException e) {
            return marks;
        }
        return marks;
    }

    public List<Mark> getUnapprovedMarks() {
        List<Mark> marks = new ArrayList<>();
        boolean approved = false;
        try {
            jdbcOperations.query(
                    "SELECT mark_id, mark_time, frame_address, user_id, confidence" +
                            " FROM marks WHERE approved = ?",
                    (resultSet, i) -> {
                        String id = resultSet.getString(MARKID);
                        Timestamp markTime = resultSet.getTimestamp(MARKTIME);
                        String frameAddress = resultSet.getString(FRAMEADDRESS);
                        String userId = resultSet.getString(USERID);
                        float confidence = resultSet.getFloat(CONFIDENCE);
                        return marks.add(new Mark(id, markTime, frameAddress, userId, confidence, approved));
                    },
                    approved);
        } catch (EmptyResultDataAccessException e) {
            return marks;
        }
        return marks;
    }

    public Mark addMark(Mark mark) {
        jdbcOperations.update(
                "INSERT INTO marks (mark_id, mark_time, frame_address, user_id, confidence, approved) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                mark.getMarkId(), mark.getMarkTime(), mark.getFrameAddress(), mark.getUserId(),
                mark.getConfidence(), mark.isApproved()
        );
        return getMarkById(mark.getMarkId());
    }

    public Mark updateMark(Mark mark) {
        jdbcOperations.update(
                "UPDATE marks SET mark_time = ?, frame_address = ?, " +
                        "user_id = ?, confidence = ?, approved = ? WHERE mark_id = ?",
                mark.getMarkTime(), mark.getFrameAddress(), mark.getUserId(),
                mark.getConfidence(), mark.isApproved(), mark.getMarkId()
                );
        return getMarkById(mark.getMarkId());
    }
}
