package ca.testing.course.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class GradeUtils {

    public double calculateAverage(List<Double> grades) {
        double average = grades.stream().mapToDouble(g -> g).average().orElse(0);
        return new BigDecimal(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
