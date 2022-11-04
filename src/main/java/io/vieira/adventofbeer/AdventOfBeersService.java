package io.vieira.adventofbeer;

import java.time.LocalDate;
import java.util.Map;

public interface AdventOfBeersService {

    Map<LocalDate, Beer> brew(Shade shade, Strength strength, Bitterness bitterness) throws OutOfAdventPeriodException;
}
