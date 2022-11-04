package io.vieira.adventofbeer;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

public class RandomAdventOfBeersService implements AdventOfBeersService {

    private final Clock clock;

    public RandomAdventOfBeersService(Clock clock) {
        this.clock = clock;
    }

    public RandomAdventOfBeersService() {
        this(Clock.systemDefaultZone());
    }

    @Override
    public Map<LocalDate, Beer> brew(Shade shade, Strength strength, Bitterness bitterness) throws OutOfAdventPeriodException {
        final var now = LocalDate.now(clock);

        final var beginningOfNovemberDate = now.withMonth(11).with(firstDayOfMonth());
        final var christmasDate = now.withMonth(12).withDayOfMonth(25);

        if (now.isBefore(beginningOfNovemberDate) || now.isAfter(christmasDate)) {
            throw new OutOfAdventPeriodException();
        }
        return Map.of();
    }
}
