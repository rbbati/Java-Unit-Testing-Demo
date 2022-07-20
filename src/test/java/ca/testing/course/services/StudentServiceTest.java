package ca.testing.course.services;

import ca.testing.course.converters.StudentConverter;
import ca.testing.course.dtos.EmailDto;
import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.exceptions.StudentInvalidException;
import ca.testing.course.models.Student;
import ca.testing.course.repositories.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentConverter studentConverter;

    @Mock
    ValidationService validationService;

    @Mock
    EmailService emailService;

    @Mock
    StudentRepository studentRepository;

    @Captor
    ArgumentCaptor<EmailDto> emailDtoArgumentCaptor;

    @Test
    void getStudents() {
        List<Student> students = buildStudentList();

        when(studentRepository.findAll())
                .thenReturn(students);

        studentService.getStudents();

        verify(studentRepository, times(1))
                .findAll();
        verify(studentConverter, times(1))
                .toDto(students);
    }

    @Test
    void saveStudent() throws StudentInvalidException {
        StudentSaveDto studentSaveDto = new StudentSaveDto();
        studentSaveDto.setEmail("test@exemple.ca");
        Student student = new Student();

        when(studentConverter.toEntity(studentSaveDto)).thenReturn(student);

        studentService.saveStudent(studentSaveDto);

        verify(validationService, times(1)).validateStudent(studentSaveDto);
        verify(studentConverter, times(1)).toEntity(studentSaveDto);
        verify(studentRepository, times(1)).save(student);
        verify(emailService, times(1)).sendEmail(emailDtoArgumentCaptor.capture());
        Assertions.assertEquals(studentSaveDto.getEmail(), emailDtoArgumentCaptor.getValue().getEmailAddress());
    }

    @Test
    void givenNullEmail_WhenSaveStudent_ThenThrowException() throws StudentInvalidException {
        StudentSaveDto studentSaveDto = new StudentSaveDto();
        doThrow(StudentInvalidException.class).when(validationService).validateStudent(studentSaveDto);
        Assertions.assertThrows(StudentInvalidException.class, () -> studentService.saveStudent(studentSaveDto));
    }

    private List<Student> buildStudentList() {
        Student student1 = new Student();
        Student student2 = new Student();

        return Arrays.asList(student1, student2);
    }
}