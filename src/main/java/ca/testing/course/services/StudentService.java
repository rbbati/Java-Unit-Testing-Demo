package ca.testing.course.services;

import ca.testing.course.converters.StudentConverter;
import ca.testing.course.dtos.StudentDto;
import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.models.Student;
import ca.testing.course.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return studentConverter.toDto(students);
    }

    public Student saveStudent(StudentSaveDto dto) {
        validationService.validateStudent(dto);
        return studentRepository.save(studentConverter.toEntity(dto));
    }
}
