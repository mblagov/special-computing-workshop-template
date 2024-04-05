package ru.spbu.apcyb.svp.tasks.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class task3Test {

    @Test
    void incorrectAmountOfArgumentsExceptionTest() {
        String[] args = {"..//"};
        assertThrows(IllegalArgumentException.class, () -> task3.main(args));
    }
    @Test
    void FileNotFoundExceptionTest() {
        String[] args = {"non-existent directory/", "src/test/resources/task3ans.txt"};
        assertThrows(FileNotFoundException.class, () -> task3.main(args));
    }
    @Test
    void FileSystemExceptionTest() {
        String[] args = {"..//", "..//"};
        assertThrows(FileSystemException.class, () -> task3.main(args));
    }

    @Test
    void exapmleTest() throws IOException {
        String pathToDirectory = "src/test/resources/task3foldersandfiles/";
        Path currentFile = Path.of("src/test/resources/task3curr.txt");
        String[] args = {pathToDirectory, currentFile.toString()};
        task3.main(args);
        boolean equals = true;

        try (BufferedReader currentReader = new BufferedReader(new FileReader(
                currentFile.toFile())); BufferedReader correctReader = new BufferedReader(
                new FileReader(Path.of("src/test/resources/task3ans.txt").toFile()))) {

            String cur = currentReader.readLine();
            String cor = correctReader.readLine();
            while (cur != null && cor != null) {
                if (!cor.equals(cur)) {
                    equals = false;
                }
                cur = currentReader.readLine();
                cor = correctReader.readLine();
            }
            if (cur != null || cor != null) {
                equals = false;
            }
        }

        assertTrue(equals);
    }

}