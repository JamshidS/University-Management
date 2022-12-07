package universitySystem.University.business.concretes;

import org.springframework.stereotype.Service;
import universitySystem.University.business.abstracts.LessonService;
import universitySystem.University.business.abstracts.StudentService;
import universitySystem.University.core.Utils.StudentModel;
import universitySystem.University.dataAccess.LessonRepository;
import universitySystem.University.dataAccess.StudentRepository;
import universitySystem.University.entities.Lesson;
import universitySystem.University.entities.Student;
import universitySystem.University.requests.StudentRequest;
import universitySystem.University.responses.LessonsResponse;
import universitySystem.University.responses.StudentListResponse;
import universitySystem.University.responses.StudentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentManager implements StudentService {
    private final StudentRepository studentRepository;
    private final LessonService lessonService;
    private final LessonRepository lessonRepository;

    public StudentManager(StudentRepository studentRepository, LessonService lessonService,
                          LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.lessonService = lessonService;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<StudentListResponse> getAll() {

        return studentRepository.findAll().stream().map(StudentModel::toStudentListResponse).collect(Collectors.toList());
    }

    @Override
    public StudentListResponse add(StudentRequest studentRequest) {
        Student student = new Student();
        List<Lesson> lessons = new ArrayList<>();
        student.setDob(studentRequest.getDob());
        student.setEntryYear(studentRequest.getEntryYear());
        student.setFullName(studentRequest.getName());
        Lesson lesson = lessonService.getLessonById(studentRequest.getLessonId());
        lessons.add(lesson);
        student.setLessons(lessons);
        return StudentModel.toStudentListResponse(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentListResponse update(Long id, StudentRequest studentRequest) {
        Optional<Student> studentINDB = studentRepository.findById(id);
        if(studentINDB.isPresent()){
            Student student = studentINDB.get();
            student.setDob(studentRequest.getDob());
            student.setEntryYear(studentRequest.getEntryYear());
            student.setFullName(studentRequest.getName());
            List<Lesson> lessons = new ArrayList<>();
            Lesson lesson = lessonService.getLessonById(studentRequest.getLessonId());
            lessons.add(lesson);
            student.setLessons(lessons);
            StudentModel.toStudentListResponse(studentRepository.save(student));
        }
        return null;
    }

    @Override
    public StudentResponse getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(StudentModel::toStudentResponse).orElse(null);
    }

    @Override
    public List<LessonsResponse> getStudentLessons(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            Student student1 = student.get();
            return StudentModel.toStudentListResponse(student1).getLessonsResponseList();
        }
        return null;
    }

}
