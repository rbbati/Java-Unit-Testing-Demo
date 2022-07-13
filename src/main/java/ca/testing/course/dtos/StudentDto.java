package ca.testing.course.dtos;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private double javaGradeAverage;
}
