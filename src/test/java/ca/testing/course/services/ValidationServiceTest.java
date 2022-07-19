package ca.testing.course.services;

import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.exceptions.StudentInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationServiceTest {

    ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }

    @Test
    void givenValidStudentSaveDto_WhenValidateStudent_ThenIsValide() throws StudentInvalidException {
        StudentSaveDto studentSavedto = buildStudentSaveDto(LocalDate.of(2000, 1, 1));
        validationService.validateStudent(studentSavedto);
    }

    @Test
    void givenNullBirthDate_WhenValidateStudent_ThenThrowException() {
        StudentSaveDto studentSavedto = buildStudentSaveDto(null);
        assertThrows(IllegalArgumentException.class,
                () -> validationService.validateStudent(studentSavedto));
    }

    @Test
    void givenFutureBirthDate_WhenValidateStudent_ThenThrowException() {
        StudentSaveDto studentSavedto = buildStudentSaveDto(LocalDate.of(2023, 1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> validationService.validateStudent(studentSavedto));
    }

    private StudentSaveDto buildStudentSaveDto(LocalDate birthDate) {
        return StudentSaveDto
                .builder()
                .birthDate(birthDate)
                .build();
    }
}