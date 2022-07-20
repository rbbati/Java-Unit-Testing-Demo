package ca.testing.course.utils;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

@Component
public class DateUtils {

    public int calculateAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            throw new IllegalArgumentException("Birth Date cannot be null");
        }
    }

    public String isItTheWeekend(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return "Yes";
        } else if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            return "Almost There";
        } else return "Nope";
    }
}
