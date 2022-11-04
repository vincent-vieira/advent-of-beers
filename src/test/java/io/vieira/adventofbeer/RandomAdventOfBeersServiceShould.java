package io.vieira.adventofbeer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RandomAdventOfBeersServiceShould {

    private final ZoneId zone = ZoneId.systemDefault();

    private AdventOfBeersService service;

    @Test
    void brewAnAdventCalendarGivenAValidPeriod() {
        service = new RandomAdventOfBeersService(Clock.fixed(
                LocalDate.of(2022, 11, 12).atStartOfDay(zone).toInstant(),
                zone
        ));

        final var result = assertDoesNotThrow(() -> service.brew(Shade.DARK, Strength.FREE, Bitterness.BITTER));

        assertEquals(Map.of(), result);
    }

    @ParameterizedTest
    @MethodSource("outsideOfPeriodDates")
    /*@ValueSource(strings = {
            "2022-12-26",
            "2022-10-26",
            "2022-01-26",
            "2023-01-26",
    })*/
    void notBrewAnAdventCalendarOutsideOfValidPeriod(LocalDate date) {
        service = new RandomAdventOfBeersService(Clock.fixed(
                date.atStartOfDay(zone).toInstant(),
                zone
        ));

        assertThrows(OutOfAdventPeriodException.class, () -> service.brew(Shade.DARK, Strength.FREE, Bitterness.BITTER));
    }

    private static Stream<Arguments> outsideOfPeriodDates() {
        return Stream.of(
                Arguments.of(LocalDate.of(2022, 12, 26)),
                Arguments.of(LocalDate.of(2022, 10, 26)),
                Arguments.of(LocalDate.of(2022, 1, 26)),
                Arguments.of(LocalDate.of(2023, 1, 26))
        );
    }
}
