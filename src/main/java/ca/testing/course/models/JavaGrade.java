package ca.testing.course.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "java_grade")
@Data
public class JavaGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "grade")
    private double grade;
}
