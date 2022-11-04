package io.vieira.adventofbeer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vieira.adventofbeer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AdventOfBeersControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdventOfBeersService adventOfBeersService;

    @Test
    void notBrewAnAdventCalendarGivenMissingParameters() throws Exception {
        mockMvc.perform(get("/advent-of-beers?shade=DARK&bitterness=BITTER"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(adventOfBeersService);
    }

    @Test
    void brewAnAdventCalendarGivenValidParameters() throws Exception {
        // Arrange
        when(adventOfBeersService.brew(any(), any(), any())).thenReturn(Map.of(
                LocalDate.of(2022, 12, 1),
                new Beer("Belzebuth")
        ));

        // Act
        mockMvc.perform(get("/advent-of-beers?shade=DARK&strength=HARDCORE&bitterness=BITTER"))

                // Assert
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "2022-12-01": { "name": "Belzebuth" }
                        }
                        """, true));

        verify(adventOfBeersService).brew(Shade.DARK, Strength.HARDCORE, Bitterness.BITTER);
        verifyNoMoreInteractions(adventOfBeersService);
    }
}
