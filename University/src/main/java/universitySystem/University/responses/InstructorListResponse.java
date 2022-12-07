package universitySystem.University.responses;

import lombok.Data;
import universitySystem.University.entities.Lesson;

import java.util.Date;
import java.util.List;

@Data
public class InstructorListResponse {
    private String name;
    private Long id;
    private Date dob;
    private List<LessonsResponse> lessonsList;
}
