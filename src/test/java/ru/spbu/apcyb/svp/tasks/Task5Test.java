package ru.spbu.apcyb.svp.tasks;

import ru.spbu.apcyb.svp.tasks.Task3;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task5Test {
    private final String wordsFilePath = ".\\src\\test\\resources\\Test5\\Test1.txt";
    private final String notExistingFilePath = ".\\src\\test\\resources\\Test5\\sdgrafgds.txt";
    private final String createdFilesDirectoryPath = ".\\src\\test\\resources\\Test5\\CreatedFiles";
    private Map<String, Integer> expected;

    @BeforeAll
    void setUp() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("foo", 2);
        expected.put("bar", 1);
        expected.put("baz", 1);
        this.expected = expected;
    }

    @Test
    void testCountWordsInFile() throws IOException {
        Map<String, Integer> actual = Task5.countWordsInFile(wordsFilePath);
        assertEquals(expected, actual);
    }

    @Test
    void testCreateFiles() throws IOException {
        Task5.createFiles(createdFilesDirectoryPath, expected, 10);
        String actualFiles = Task3.getString(new Task3(createdFilesDirectoryPath).getList());
        String expectedFiles = ".\\src\\test\\resources\\Test5\\CreatedFiles\\bar.txt\n" +
                ".\\src\\test\\resources\\Test5\\CreatedFiles\\baz.txt\n" +
                ".\\src\\test\\resources\\Test5\\CreatedFiles\\foo.txt\n";

        assertEquals(expectedFiles, actualFiles);
    }

    @Test
    void testCountWordsInNotExistingFile() {
        assertThrows(FileNotFoundException.class, () -> Task5.countWordsInFile(notExistingFilePath));
    }

    @Test
    void testCountWordsInDirectory() {
        assertThrows(IllegalArgumentException.class, () -> Task5.countWordsInFile(".\\src\\test\\resources\\Test5"));
    }

    @Test
    void testWriteToExistingFile() {
        assertThrows(IllegalArgumentException.class, () -> Task5.writeToFile(wordsFilePath, expected));
    }

    @Test
    void testWriteToDirectory() {
        assertThrows(IllegalArgumentException.class, () -> Task5.writeToFile(createdFilesDirectoryPath, expected));
    }

    @Test
    void testCreateFilesInZeroThreads() {
        assertThrows(IllegalArgumentException.class, () -> Task5.createFiles(createdFilesDirectoryPath, expected, 0));
    }

    @Test
    void testCreateFilesInNotExistingDirectory() {
        assertThrows(FileNotFoundException.class, () -> Task5.createFiles(createdFilesDirectoryPath + "NotExist", expected, 10));
    }

    @Test
    void testCreateFilesInNotADirectory() {
        assertThrows(IllegalArgumentException.class, () -> Task5.createFiles(wordsFilePath, expected, 10));
    }
}
