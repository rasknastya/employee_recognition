package it.sevenbits.core.repository.marks;

import it.sevenbits.core.model.Mark;

import java.sql.Timestamp;
import java.util.List;

public interface MarkRepository {
    Mark getMarkById(String markId);
    List<Mark> getMarksByUserId(String userId, Timestamp timestamp);
    List<Mark> getAllMarks(Timestamp timestamp);
    List<Mark> getUnapprovedMarks(Timestamp timestamp);
    Mark addMark(Mark mark);
    Mark updateMark(Mark mark);

    void removeMark(String markId);
}
