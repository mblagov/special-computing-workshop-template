package ru.spbu.apcyb.svp.tasks;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;

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
    void exceptionTest3() {
        String[] args = {"..//"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Task3.main(args));
    }

    @Test
    void exceptionTest4() {
        String[] args = {"..//", "..//", "..//"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Task3.main(args));
    }

    @Test
    void getListFiles() throws IOException {
        String filepath = "C:\\Users\\Артем\\IdeaProjects\\special-computing-workshop-template\\src\\main\\resources";
        String[] args = {filepath, "result.txt"};
        Task3.main(args);
        boolean equals = true;
        try (BufferedReader resReader = new BufferedReader(new FileReader(Path.of("result.txt").toFile())); BufferedReader ansReader = new BufferedReader(new FileReader(Path.of("answer1.txt").toFile()))) {
            String resLine = resReader.readLine();
            String ansLine = ansReader.readLine();
            while (resLine != null && ansLine != null) {
                if (!ansLine.equals(resLine)) {
                    equals = false;
                }
                resLine = resReader.readLine();
                ansLine = ansReader.readLine();
            }
            if ((resLine != null && ansLine == null) || (ansLine != null && resLine == null)) {
                equals = false;
            }
        }
        assertTrue(equals);

    }


    @Test
    void FileFounderTest() {
        String[] args = {"..//", "answer.txt"};
        assertDoesNotThrow(() -> Task3.main(args));
    }
}