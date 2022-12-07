package universitySystem.University.responses;

import lombok.Data;
import universitySystem.University.entities.Lesson;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentResponse {
    private String name;
    private int entryYear;
    private int graduationYear = entryYear+4;
    private List<Lesson> lessons = new ArrayList<>();
}
