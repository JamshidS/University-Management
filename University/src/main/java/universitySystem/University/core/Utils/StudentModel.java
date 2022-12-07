package universitySystem.University.core.Utils;

import universitySystem.University.entities.Lesson;
import universitySystem.University.entities.Student;
import universitySystem.University.responses.LessonsResponse;
import universitySystem.University.responses.StudentListResponse;
import universitySystem.University.responses.StudentResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public static StudentListResponse toStudentListResponse(Student student){
        StudentListResponse response =  new StudentListResponse();
        response.setDob(student.getDob());
        response.setId(student.getId());
        response.setName(student.getFullName());
        response.setEntryYear(student.getEntryYear());
        List<Lesson> lessons = student.getLessons();
        List<LessonsResponse> responseList = new ArrayList<>();
        if(student.getLessons()!=null){
            for (Lesson l:lessons){
                LessonsResponse listResponse = LessonModel.toLessonsListResponse(l);
                responseList.add(listResponse);
            }
        }
        response.setLessonsResponseList(responseList);
        return response;
    }
    public static StudentResponse toStudentResponse(Student student){
        StudentResponse response = new StudentResponse();
        response.setName(student.getFullName());
        response.setEntryYear(student.getEntryYear());
        return response;

    }
}
