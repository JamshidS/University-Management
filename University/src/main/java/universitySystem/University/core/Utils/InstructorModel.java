package universitySystem.University.core.Utils;

import universitySystem.University.entities.Instructor;
import universitySystem.University.entities.Lesson;
import universitySystem.University.responses.InstructorListResponse;
import universitySystem.University.responses.InstructorResponse;
import universitySystem.University.responses.LessonsResponse;

import java.util.ArrayList;
import java.util.List;

public class InstructorModel {
    public static InstructorListResponse toInstructorListResponse(Instructor instructor){
        InstructorListResponse instructorListResponse = new InstructorListResponse();
        instructorListResponse.setName(instructor.getName());
        instructorListResponse.setId(instructor.getId());
        instructorListResponse.setDob(instructor.getDob());
        List<Lesson> lessons = instructor.getLessons();
        List<LessonsResponse> lessonsResponseList = new ArrayList<>();
        if(instructor.getLessons()!=null){
            for (Lesson l:lessons){
                LessonsResponse response = LessonModel.toLessonsListResponse(l);
                lessonsResponseList.add(response);
            }
        }
        instructorListResponse.setLessonsList(lessonsResponseList);
        return instructorListResponse;
    }

    public static InstructorResponse toInstructorResponse(Instructor instructor){
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setName(instructor.getName());
        return instructorResponse;
    }
}
