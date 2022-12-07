package universitySystem.University.business.abstracts;

import universitySystem.University.entities.Instructor;
import universitySystem.University.requests.InstructorRequest;
import universitySystem.University.responses.InstructorListResponse;
import universitySystem.University.responses.InstructorResponse;
import universitySystem.University.responses.LessonsResponse;

import java.util.List;

public interface InstructorService {
    List<InstructorListResponse> getAll();
    InstructorResponse add(InstructorRequest request) throws Exception;
    void delete(Long Id);
    InstructorResponse update(Long id,InstructorRequest request) throws Exception;
    InstructorResponse getById(Long id);
    Instructor getInstructorById(Long id);
    List<LessonsResponse> getInstructorLessons(Long id);

    void update(Instructor request) throws Exception;
}
