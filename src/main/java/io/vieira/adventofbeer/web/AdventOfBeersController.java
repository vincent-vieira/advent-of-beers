package io.vieira.adventofbeer.web;

import io.vieira.adventofbeer.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/advent-of-beers")
public class AdventOfBeersController {

    private final AdventOfBeersService adventOfBeersService;

    public AdventOfBeersController(AdventOfBeersService adventOfBeersService) {
        this.adventOfBeersService = adventOfBeersService;
    }

    @GetMapping
    public Map<LocalDate, Beer> createAdventCalendar(
            @RequestParam Shade shade,
            @RequestParam Strength strength,
            @RequestParam Bitterness bitterness) throws OutOfAdventPeriodException {
        return adventOfBeersService.brew(shade, strength, bitterness);
    }
}
