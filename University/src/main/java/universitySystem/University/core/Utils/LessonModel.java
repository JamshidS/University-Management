package universitySystem.University.core.Utils;

import universitySystem.University.entities.Lesson;
import universitySystem.University.responses.LessonsResponse;

public class LessonModel {
    public static LessonsResponse toLessonsListResponse(Lesson lesson){
        LessonsResponse lessonsResponse = new LessonsResponse();
        lessonsResponse.setName(lesson.getName());
        lessonsResponse.setId(lesson.getId());
        if(lesson.getInstructor()!=null){
            lessonsResponse.setInstructorID(lesson.getInstructor().getId());
        }
        return lessonsResponse;
    }
}
