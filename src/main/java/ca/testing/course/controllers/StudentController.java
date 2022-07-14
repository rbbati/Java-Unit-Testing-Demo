package ca.testing.course.controllers;

import ca.testing.course.dtos.StudentDto;
import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.models.Student;
import ca.testing.course.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Student> newStudent(@RequestBody StudentSaveDto dto) {
        return ResponseEntity.ok(studentService.saveStudent(dto));
    }
}
