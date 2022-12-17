package ru.spbu.apcyb.svp.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static ru.spbu.apcyb.svp.tasks.Task5.*;

/**
 * Тесты для задания 5.
 */
public class Task5Test {

    @Test
    void isText() throws IOException {
        Assertions.assertTrue(getWordCount("Harry_Potter.txt"));
    }

}
