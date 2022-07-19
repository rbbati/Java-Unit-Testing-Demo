package ca.testing.course.converters;

import ca.testing.course.dtos.StudentDto;
import ca.testing.course.models.Student;
import ca.testing.course.utils.DateUtils;
import ca.testing.course.utils.GradeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentConverterTest {

    @InjectMocks
    StudentConverter studentConverter;
    @Mock
    DateUtils dateUtils;
    @Mock
    GradeUtils gradeUtils;


    @Test
    void givenStudent_WhenConvertToDto_ThenReturnDt0() {
        when(dateUtils.calculateAge(any(LocalDate.class))).thenReturn(30);
        when(gradeUtils.calculateAverage(any(ArrayList.class))).thenReturn(0d);

        Student student = buildStudent();

        StudentDto studentDto = studentConverter.toDto(student);

        Assertions.assertEquals(student.getId(), studentDto.getId());
        Assertions.assertEquals(student.getFirstname(), studentDto.getFirstName());
        Assertions.assertEquals(student.getLastname(), studentDto.getLastName());
        Assertions.assertEquals(30, studentDto.getAge());
        Assertions.assertEquals(0d, studentDto.getJavaGradeAverage());
    }

    private Student buildStudent() {
        return Student.builder()
                .id(1L)
                .firstname("Vin")
                .lastname("Diesel")
                .birthDate(LocalDate.now())
                .javaGrades(new ArrayList<>())
                .build();
    }

}