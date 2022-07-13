package ca.testing.course.converters;

import ca.testing.course.dtos.StudentDto;
import ca.testing.course.models.JavaGrade;
import ca.testing.course.models.Student;
import ca.testing.course.utils.DateUtils;
import ca.testing.course.utils.GradeUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    public List<StudentDto> toDto(List<Student> students) {
        return students.stream().map(this::toDto).collect(Collectors.toList());
    }

    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstname());
        studentDto.setLastName(student.getLastname());
        studentDto.setAge(DateUtils.calculateAge(student.getBirthDate()));
        studentDto.setJavaGradeAverage(GradeUtils.calculateAverage(getJavaGrades(student.getJavaGrades())));

        return studentDto;
    }

    private List<Double> getJavaGrades(List<JavaGrade> javaGrades) {
        return javaGrades.stream().map(JavaGrade::getGrade).collect(Collectors.toList());
    }
}
