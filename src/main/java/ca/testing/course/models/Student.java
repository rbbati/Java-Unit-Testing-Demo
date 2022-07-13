package ca.testing.course.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private LocalDate birthDate;
    @OneToMany
    @JoinColumn(name = "student_id")
    private List<JavaGrade> javaGrades;
}
