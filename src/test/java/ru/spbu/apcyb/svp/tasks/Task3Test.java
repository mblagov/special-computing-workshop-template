package ru.spbu.apcyb.svp.tasks;


import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 3.
 */
class Task3Test {

    @Test
    void exceptionTest1() {
        String[] args = {"NotExistingDirectory", "answer.txt"};
        assertThrows(FileNotFoundException.class, () -> Task3.main(args));
    }


    @Test
    void exceptionTest2() {
        String[] args = {"..//", "..//"};
        assertThrows(NotDirectoryException.class, () -> Task3.main(args));
    }

    @Test
    void FileFounderTest() {
        String[] args = {"..//", "answer.txt"};
        assertDoesNotThrow(() -> Task3.main(args));
    }
}