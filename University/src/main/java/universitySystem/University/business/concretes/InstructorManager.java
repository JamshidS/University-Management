package universitySystem.University.business.concretes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import universitySystem.University.business.abstracts.InstructorService;
import universitySystem.University.business.abstracts.LessonService;
import universitySystem.University.core.Utils.InstructorModel;
import universitySystem.University.dataAccess.InstructorRepository;
import universitySystem.University.entities.Instructor;
import universitySystem.University.entities.Lesson;
import universitySystem.University.requests.InstructorRequest;
import universitySystem.University.responses.InstructorListResponse;
import universitySystem.University.responses.InstructorResponse;
import universitySystem.University.responses.LessonsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorManager implements InstructorService {
    private final InstructorRepository instructorRepository;


    public InstructorManager(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;

    }


    @Override
    public List<InstructorListResponse> getAll() {

        return instructorRepository.findAll().stream().map(InstructorModel::toInstructorListResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorResponse add(InstructorRequest request) throws Exception {
        if (isNameExits(request.getName())) {
            throw new Exception("The instructor name is already exits!");
        }
        Instructor instructor = new Instructor();
        List<Lesson> lessons = new ArrayList<>();
        instructor.setName(request.getName());
        instructor.setDob(request.getDob());
//       // Optional<Lesson> l1 = lessonRepository.findById(request.getLessonId());
//        if(l1.isPresent()){
//            Lesson l2 = l1.get();
//            lessons.add(l2);
//            instructor.setLessons(lessons);
//        }
        return InstructorModel.toInstructorResponse(instructorRepository.save(instructor));
    }

    @Override
    public void delete(Long Id) {
        instructorRepository.deleteById(Id);
    }

    @Override
    public InstructorResponse update(Long id, InstructorRequest request) throws Exception {
        if (isNameExits(request.getName())) {
            throw new Exception("The instructor name is already exits!");
        }
        Optional<Instructor> instructorInDB = instructorRepository.findById(id);
        if (instructorInDB.isPresent()) {
            Instructor instructor = new Instructor();
            instructor.setDob(request.getDob());
            instructor.setName(request.getName());
            return InstructorModel.toInstructorResponse(instructorRepository.save(instructor));
        }
        return null;
    }

    @Override
    public void update(Instructor request) throws Exception {

        instructorRepository.save(request);
    }

    @Override
    public InstructorResponse getById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.map(InstructorModel::toInstructorResponse).orElse(null);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public List<LessonsResponse> getInstructorLessons(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            Instructor instructor1 = instructor.get();
            return InstructorModel.toInstructorListResponse(instructor1).getLessonsList();
        }
        return null;
    }


    private boolean isNameExits(String data) {
        return instructorRepository.existsByName(data);
    }
}
