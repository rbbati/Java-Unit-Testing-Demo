package ca.testing.course.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.time.LocalDate;
import java.util.stream.Stream;

class DateUtilsTest {

    DateUtils dateUtils;

    private static Stream<Arguments> birthDateArgumentsProvider() {
        return Stream.of(
                Arguments.of(LocalDate.of(2000, 7, 22), 21),
                Arguments.of(LocalDate.of(1998, 2, 15), 24),
                Arguments.of(LocalDate.of(2002, 11, 9), 19)
        );
    }

    private static Stream<Arguments> weekEndArgumentsProvider() {
        return Stream.of(
                Arguments.of(LocalDate.of(2022, 7, 17), "Yes"),
                Arguments.of(LocalDate.of(2022, 7, 16), "Yes"),
                Arguments.of(LocalDate.of(2022, 7, 15), "Almost There"),
                Arguments.of(LocalDate.of(2022, 7, 14), "Nope"),
                Arguments.of(LocalDate.of(2022, 7, 13), "Nope"),
                Arguments.of(LocalDate.of(2022, 7, 12), "Nope"),
                Arguments.of(LocalDate.of(2022, 7, 11), "Nope")
        );
    }

    @BeforeEach
    void setUp() {
        dateUtils = new DateUtils();
    }

    @ParameterizedTest(name = "Given Birth Date [{0}] and Expecting Age [{1}]")
    @MethodSource("birthDateArgumentsProvider")
    void givenValidBirthDate_WhenCalculateAge_ThenReturnValue(LocalDate givenBirthDate, int expectedAge) {
        LocalDate givenNow = LocalDate.of(2022, 7, 18);

        try (MockedStatic<LocalDate> localDateMockedStatic = Mockito.mockStatic(LocalDate.class, InvocationOnMock::callRealMethod)) {
            localDateMockedStatic
                    .when(() -> LocalDate.now())
                    .thenReturn(givenNow);
            Assertions.assertEquals(expectedAge, dateUtils.calculateAge(givenBirthDate));
        }
    }

    @Test
    void givenMonday_WhenIsItWeekEnd_ThenReturnNope() {
        LocalDate givenMonday = LocalDate.of(2022, 7, 11);
        Assertions.assertEquals("Nope", dateUtils.isItTheWeekend(givenMonday));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "17,Yes",
            "16,Yes",
            "15,Almost There",
            "14,Nope",
            "13,Nope",
            "12,Nope",
            "11,Nope"
    })
//    @MethodSource("weekEndArgumentsProvider")
    void givenDate_WhenIsItWeekEnd_ThenReturnExpectedValue(int dayOfMonth, String expectedValue) {
        LocalDate givenDate = LocalDate.of(2022, 7, dayOfMonth);
        Assertions.assertEquals(expectedValue, dateUtils.isItTheWeekend(givenDate));
    }
}