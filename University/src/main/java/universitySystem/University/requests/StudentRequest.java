package universitySystem.University.requests;

import lombok.Data;
import universitySystem.University.responses.LessonsResponse;

import java.util.Date;
import java.util.List;

@Data
public class StudentRequest {
    private String name;
    private Date dob;
    private int entryYear;
    private Long lessonId;
}
