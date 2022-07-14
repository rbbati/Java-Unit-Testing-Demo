package ca.testing.course.services;

import ca.testing.course.dtos.StudentSaveDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public void validateStudent(StudentSaveDto dto) {
        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid Birth Date");
        }
    }
}
