package universitySystem.University.business.concretes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import universitySystem.University.business.abstracts.InstructorService;
import universitySystem.University.business.abstracts.LessonService;
import universitySystem.University.core.Utils.LessonModel;
import universitySystem.University.dataAccess.LessonRepository;
import universitySystem.University.entities.Instructor;
import universitySystem.University.entities.Lesson;
import universitySystem.University.requests.LessonRequest;
import universitySystem.University.responses.LessonsResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonManager implements LessonService {
    private final LessonRepository lessonRepository;
    private final InstructorService instructorService;

    public LessonManager(LessonRepository lessonRepository, InstructorService instructorService) {
        this.lessonRepository = lessonRepository;
        this.instructorService = instructorService;
    }


    @Override
    public List<LessonsResponse> getAll() {
        return lessonRepository.findAll().stream().map(LessonModel::toLessonsListResponse).collect(Collectors.toList());
    }

    @Override
    public LessonsResponse add(LessonRequest request) throws Exception {
        if (isNameExits(request.getName())) {
            throw new Exception("Lesson is already Exits");
        }
        Lesson lesson = new Lesson();
        lesson.setName(request.getName());
        lesson.setInstructor(instructorService.getInstructorById(request.getInstructorId()));

        return LessonModel.toLessonsListResponse(lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public LessonsResponse update(Long id, LessonRequest request) throws Exception {
        if (isNameExits(request.getName())) {
            throw new Exception("Lesson is already Exits");
        }
        Optional<Lesson> lessonIndDB = lessonRepository.findById(id);
        if (lessonIndDB.isPresent()) {
            Lesson lesson = lessonIndDB.get();
            lesson.setName(request.getName());
            lesson.setInstructor(instructorService.getInstructorById(request.getInstructorId()));
            return LessonModel.toLessonsListResponse(lessonRepository.save(lesson));
        }
        return null;
    }

    @Override
    public LessonsResponse getById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.map(LessonModel::toLessonsListResponse).orElse(null);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    private boolean isNameExits(String data) {
        for (Lesson lesson : lessonRepository.findAll()) {
            return true;
        }
        return false;
    }


    @Override
    public void addInstructorLessons(Long lessonId, Long instructorId) throws Exception {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Not found"));
        instructor.getLessons().add(lesson);
        instructorService.update(instructor);
    }

}


