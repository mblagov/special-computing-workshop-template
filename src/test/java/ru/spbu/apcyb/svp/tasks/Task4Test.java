package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Тесты для задания 4.
 */
public class Task4Test {
    @Test
    void readFile() throws IOException {
        var values = Task4.readValues("./src/test/resources/task-3/values-10.txt");
        Assertions.assertIterableEquals(values, List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0));
    }

    @Test
    void calculateTans() {
        var values = List.of(0.0, 0.78539, 2.35619, 3.14159, 100.0);
        var expected = List.of(0.0, 1.0, -1.0, 0.0, -0.58721);
        var tans = Task4.calculateTangents(values);

        Assertions.assertEquals(tans.size(), expected.size(), "Wrong result length");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), tans.get(i), 1e-4, "Wrong value at index " + i);
        }
    }

    @Test
    void calculateTansMultithread() {
        var values = List.of(0.0, 0.78539, 2.35619, 3.14159, 100.0);
        var expected = List.of(0.0, 1.0, -1.0, 0.0, -0.58721);
        var tans = Task4.calculateTangentsMultithread(values);

        Assertions.assertEquals(tans.size(), expected.size(), "Wrong result length");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), tans.get(i), 1e-4, "Wrong value at index " + i);
        }
    }
}
