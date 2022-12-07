package universitySystem.University.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universitySystem.University.business.abstracts.InstructorService;
import universitySystem.University.business.abstracts.LessonService;
import universitySystem.University.requests.InstructorRequest;
import universitySystem.University.requests.LessonRequest;
import universitySystem.University.responses.InstructorListResponse;
import universitySystem.University.responses.InstructorResponse;
import universitySystem.University.responses.LessonsResponse;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    private final LessonService lessonService;

    @Autowired
    public InstructorController(InstructorService instructorService, LessonService lessonService) {
        this.instructorService = instructorService;
        this.lessonService = lessonService;
    }
    @GetMapping
    public ResponseEntity<List<InstructorListResponse>> getAll(){
        List<InstructorListResponse> listResponses = instructorService.getAll();
        if(listResponses.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return new  ResponseEntity<>(listResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> getById(@PathVariable Long id){
        InstructorResponse instructorResponse = instructorService.getById(id);
        if(Objects.nonNull(instructorResponse)){
            return new ResponseEntity<>(instructorResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        instructorService.delete(id);
    }

    @PostMapping
    public ResponseEntity<InstructorResponse> add(@RequestBody InstructorRequest request) throws Exception {
        InstructorResponse response = instructorService.add(request);
        if(Objects.nonNull(response)){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponse> update(@RequestBody InstructorRequest instructorRequest,@PathVariable Long id) throws Exception {
        InstructorResponse instructorResponse = instructorService.update(id,instructorRequest);
        if(Objects.nonNull(instructorResponse)){
            return new ResponseEntity<>(instructorResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/LessonsList")
    public ResponseEntity<List<LessonsResponse>> getInstructorLessons(@PathVariable Long id){
        List<LessonsResponse> lessonsResponses = instructorService.getInstructorLessons(id);
        if (Objects.nonNull(lessonsResponses)){
            return new ResponseEntity<>(lessonsResponses,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/{id}/LessonsList")
    public void addInstructorLessons(@RequestBody LessonRequest request, @PathVariable Long instructorId) throws Exception {
        lessonService.addInstructorLessons(request.getId(),instructorId);
    }

}
