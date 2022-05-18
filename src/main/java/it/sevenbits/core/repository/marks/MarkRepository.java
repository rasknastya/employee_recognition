package it.sevenbits.core.repository.marks;

import it.sevenbits.core.model.Mark;

import java.sql.Timestamp;
import java.util.List;

/**
 * The interface Mark repository.
 */
public interface MarkRepository {
    /**
     * Gets mark by id.
     *
     * @param markId the mark id
     * @return the mark by id
     */
    Mark getMarkById(String markId);
    List<Mark> getMarksByUserId(String userId, Timestamp timestamp);
    List<Mark> getAllMarks(Timestamp timestamp);
    List<Mark> getUnapprovedMarks(Timestamp timestamp);

    Mark addMark(Mark mark);

    /**
     * Update mark mark.
     *
     * @param mark the mark
     * @return the mark
     */
    Mark updateMark(Mark mark);

    void removeMark(String markId);
}
