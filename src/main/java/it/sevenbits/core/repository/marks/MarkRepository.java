package it.sevenbits.core.repository.marks;

import it.sevenbits.core.model.Mark;

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

    /**
     * Gets marks by user id.
     *
     * @param userId the user id
     * @return the marks by user id
     */
    List<Mark> getMarksByUserId(String userId);

    /**
     * Gets all marks.
     *
     * @return the all marks
     */
    List<Mark> getAllMarks();

    /**
     * Gets unapproved marks.
     *
     * @return the unapproved marks
     */
    List<Mark> getUnapprovedMarks();

    /**
     * Add mark mark.
     *
     * @param mark the mark
     * @return the mark
     */
    Mark addMark(Mark mark);

    /**
     * Update mark mark.
     *
     * @param mark the mark
     * @return the mark
     */
    Mark updateMark(Mark mark);
}
