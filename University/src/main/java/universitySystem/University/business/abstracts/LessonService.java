package universitySystem.University.business.abstracts;

import universitySystem.University.entities.Lesson;
import universitySystem.University.requests.LessonRequest;
import universitySystem.University.responses.LessonsResponse;

import java.util.List;

public interface LessonService {
    List<LessonsResponse> getAll();
    LessonsResponse add(LessonRequest request) throws Exception;
    void delete(Long id);
    LessonsResponse update(Long id,LessonRequest request) throws Exception;
    LessonsResponse getById(Long id);
    Lesson getLessonById(Long id);
    public void addInstructorLessons(Long lessonId, Long instructorId) throws Exception;
}
