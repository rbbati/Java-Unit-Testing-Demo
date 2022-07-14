package ca.testing.course.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int age;
    private double javaGradeAverage;
}
