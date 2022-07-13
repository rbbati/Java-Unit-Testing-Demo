package ca.testing.course.utils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static int calculateAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            throw new IllegalArgumentException("Birth Date cannot be null");
        }
    }
}
