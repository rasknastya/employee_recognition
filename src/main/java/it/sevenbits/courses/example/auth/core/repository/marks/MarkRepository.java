package it.sevenbits.courses.example.auth.core.repository.marks;

import it.sevenbits.courses.example.auth.core.model.Mark;

import java.util.List;

public interface MarkRepository {
    Mark getMarkById(String markId);
    List<Mark> getMarksByUserId(String userId);
    List<Mark> getAllMarks();
    List<Mark> getUnapprovedMarks();
    Mark addMark(Mark mark);
    Mark updateMark(Mark mark);
}
