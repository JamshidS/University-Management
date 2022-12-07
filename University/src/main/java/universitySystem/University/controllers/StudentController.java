package universitySystem.University.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universitySystem.University.business.abstracts.StudentService;
import universitySystem.University.requests.StudentRequest;
import universitySystem.University.responses.LessonsResponse;
import universitySystem.University.responses.StudentListResponse;
import universitySystem.University.responses.StudentResponse;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/Students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }
    @GetMapping("/getall")
    public ResponseEntity<List<StudentListResponse>> getAll(){
        List<StudentListResponse> responseList = studentService.getAll();
        if(responseList.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return new  ResponseEntity<>(responseList, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(Long id){
        StudentResponse studentResponse = studentService.getById(id);
        if(Objects.nonNull(studentResponse)){
            return new ResponseEntity<>(studentResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentService.delete(id);
    }

    @PostMapping("/add")
    public ResponseEntity<StudentListResponse> add(@RequestBody StudentRequest studentRequest){
        StudentListResponse studentListResponse = studentService.add(studentRequest);
        if(Objects.nonNull(studentListResponse)){
            return new ResponseEntity<>(studentListResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentListResponse> update(@PathVariable Long id,@RequestBody StudentRequest studentRequest){
        StudentListResponse studentListResponse = studentService.update(id,studentRequest);
        if(Objects.nonNull(studentListResponse)){
            return new ResponseEntity<>(studentListResponse,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}/StudentLessons")
    public ResponseEntity<List<LessonsResponse>> getStudentLessons(Long id){
        List<LessonsResponse> lessonsResponses = studentService.getStudentLessons(id);
        if (Objects.nonNull(lessonsResponses)){
            return new ResponseEntity<>(lessonsResponses,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }


}
