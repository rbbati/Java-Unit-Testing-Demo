package ca.testing.course.services;

import ca.testing.course.converters.StudentConverter;
import ca.testing.course.dtos.EmailDto;
import ca.testing.course.dtos.StudentDto;
import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.exceptions.StudentInvalidException;
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

    @Autowired
    private EmailService emailService;

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return studentConverter.toDto(students);
    }

    public Student saveStudent(StudentSaveDto dto) throws StudentInvalidException {
        validationService.validateStudent(dto);
        Student savedStudent = studentRepository.save(studentConverter.toEntity(dto));
        EmailDto emailDto = buildEmail(dto.getEmail());
        emailService.sendEmail(emailDto);
        return savedStudent;
    }

    private EmailDto buildEmail(String email) {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailBody("Welcome");
        emailDto.setEmailSubject("Confirmation");
        emailDto.setEmailAddress(email);
        return emailDto;
    }
}
