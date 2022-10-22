package ru.spbu.apcyb.svp.tasks;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 3.
 */
class Task3Test {

    @Test
    void test2() throws IOException {
        FileWriter resFile = new FileWriter("RESULT.txt", false);
        FileNotFoundException thrown =
                assertThrows(FileNotFoundException.class, () -> Task3.getFileTree( resFile, "123"));
        assertEquals("java.io.FileNotFoundException", thrown.toString());
    }

    @Test
    void test1() throws IOException {
        FileWriter resFile = new FileWriter("RESULT.txt", false);
        boolean thrown = true;
        try {
            Task3.getFileTree( resFile, "..//");
        } catch (IOException e) {
            thrown = false;
        }
        assertTrue(thrown);
    }

}
