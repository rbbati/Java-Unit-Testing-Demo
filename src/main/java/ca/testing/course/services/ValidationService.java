package ca.testing.course.services;

import ca.testing.course.dtos.StudentSaveDto;
import ca.testing.course.exceptions.StudentInvalidException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public void validateStudent(StudentSaveDto dto) throws StudentInvalidException {
        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now()) || dto.getEmail() == null) {
            throw new StudentInvalidException("Invalid student");
        }
    }
}
