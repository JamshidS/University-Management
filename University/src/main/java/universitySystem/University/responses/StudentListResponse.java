package universitySystem.University.responses;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentListResponse {
    private String name;
    private Long id;
    private Date dob;
    private int entryYear;
    private int graduationYear=entryYear+4;
    private List<LessonsResponse> lessonsResponseList;
}
