package ca.testing.course.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.time.LocalDate;

class DateUtilsTest {

    DateUtils dateUtils;

    @BeforeEach
    void setUp() {
        dateUtils = new DateUtils();
    }

    @Test
    void givenValidBirthDate_WhenCalculateAge_ThenReturnValue() {
        LocalDate givenNow = LocalDate.of(2022, 7, 18);
        LocalDate givenBirthDate = LocalDate.of(2000, 7, 18);

        MockedStatic<LocalDate> localDateMockedStatic = Mockito.mockStatic(LocalDate.class, InvocationOnMock::callRealMethod);

        localDateMockedStatic.when(
                () -> LocalDate.now()
        ).thenReturn(givenNow);

        Assertions.assertEquals(22, dateUtils.calculateAge(givenBirthDate));
    }

}