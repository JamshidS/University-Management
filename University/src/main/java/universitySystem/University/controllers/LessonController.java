package universitySystem.University.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universitySystem.University.business.abstracts.LessonService;
import universitySystem.University.requests.LessonRequest;
import universitySystem.University.responses.LessonsResponse;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/Lessons")
public class LessonController {
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<LessonsResponse>> getAll(){
        List<LessonsResponse> lessonsResponses = service.getAll();
        if(lessonsResponses.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(lessonsResponses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LessonsResponse> getById(@PathVariable Long id){
        LessonsResponse lessonsResponse = service.getById(id);
        if(Objects.nonNull(lessonsResponse)){
            return new ResponseEntity<>(lessonsResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<LessonsResponse> add(@RequestBody LessonRequest lessonRequest) throws Exception {
        LessonsResponse lessonsResponse =  service.add(lessonRequest);
        if(Objects.nonNull(lessonsResponse)){
            return new ResponseEntity<>(lessonsResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<LessonsResponse> update(@PathVariable Long id,@RequestBody LessonRequest lessonRequest) throws Exception {
        LessonsResponse lessonsResponse =  service.update(id,lessonRequest);
        if(Objects.nonNull(lessonsResponse)){
            return new ResponseEntity<>(lessonsResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

}
