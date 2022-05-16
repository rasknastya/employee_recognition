package it.sevenbits.core.repository.marks;

import it.sevenbits.core.model.Mark;

import java.util.List;

public interface MarkRepository {
    Mark getMarkById(String markId);
    List<Mark> getMarksByUserId(String userId);
    List<Mark> getAllMarks();
    List<Mark> getUnapprovedMarks();
    Mark addMark(Mark mark);
    Mark updateMark(Mark mark);
}
