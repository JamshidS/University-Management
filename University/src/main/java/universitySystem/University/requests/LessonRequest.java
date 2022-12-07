package universitySystem.University.requests;

import lombok.Data;
import universitySystem.University.entities.Instructor;
import universitySystem.University.responses.StudentResponse;

import java.util.List;

@Data
public class LessonRequest {
    private String name;
    private Long id;
    private Long instructorId;

}
