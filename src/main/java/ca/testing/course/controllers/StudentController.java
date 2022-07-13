package ca.testing.course.controllers;

import ca.testing.course.dtos.StudentDto;
import ca.testing.course.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/students")
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }
}
