package ru.spbu.apcyb.svp.tasks;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
class Task1Test {

    @Test
    void emptyCases() {
        List<Long> emptyList = List.of();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.getCombinations(5, emptyList);
        });

        List<Long> dummyList = List.of(1L, 100L);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Task1.getCombinations(0, dummyList);
        });
    }

    @Test
    void expectedConditions() {
        Assertions.assertIterableEquals(
            Task1.getCombinations(5, List.of(1L)),
            List.of(List.of(1L, 1L, 1L, 1L, 1L))
        );
        Assertions.assertIterableEquals(
            Task1.getCombinations(5, List.of(1L, 2L)),
            List.of(
                List.of(2L, 2L, 1L),
                List.of(2L, 1L, 1L, 1L),
                List.of(1L, 1L, 1L, 1L, 1L)
            )
        );
        Assertions.assertIterableEquals(
            Task1.getCombinations(5, List.of(1L, 2L, 3L)),
            List.of(
                List.of(3L, 2L),
                List.of(3L, 1L, 1L),
                List.of(2L, 2L, 1L),
                List.of(2L, 1L, 1L, 1L),
                List.of(1L, 1L, 1L, 1L, 1L)
            )
        );
    }
}
