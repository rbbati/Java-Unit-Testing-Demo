package ca.testing.course.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GradeUtils {

    public static double calculateAverage(List<Double> grades) {
        double average = grades.stream().mapToDouble(g -> g).average().orElse(0);
        return new BigDecimal(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
