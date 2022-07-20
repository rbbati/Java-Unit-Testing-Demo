package ca.testing.course.controllers;

import ca.testing.course.dtos.StudentDto;
import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.exceptions.StudentInvalidException;
import ca.testing.course.models.Student;
import ca.testing.course.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Student> newStudent(@RequestBody StudentSaveDto dto, WebRequest request) throws StudentInvalidException {
        UUID trackingId = UUID.randomUUID();
        log.info("Receiving request [{}]", trackingId);
        request.setAttribute("trackingId", trackingId, RequestAttributes.SCOPE_REQUEST);
        return ResponseEntity.ok(studentService.saveStudent(dto));
    }
}
