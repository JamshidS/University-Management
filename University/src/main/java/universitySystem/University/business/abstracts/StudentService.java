package universitySystem.University.business.abstracts;

import universitySystem.University.requests.StudentRequest;
import universitySystem.University.responses.LessonsResponse;
import universitySystem.University.responses.StudentListResponse;
import universitySystem.University.responses.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentListResponse> getAll();
    StudentListResponse add(StudentRequest studentRequest);
    void delete(Long id);
    StudentListResponse update(Long id,StudentRequest studentRequest);
    StudentResponse getById(Long id);

    List<LessonsResponse> getStudentLessons(Long id);
}
