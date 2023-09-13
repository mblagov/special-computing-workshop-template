package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 5.
 */
class Task5Test {

    @Test
    void writeWordsToNonExistingFile() throws IOException {
        boolean fileDidNotExist = false;
        boolean fileExists = false;
        boolean check = false;
        Path path = Path.of("ns" + ".txt");
        if (!Files.exists(path)) {
            Task5.writeWordToNonExistingFile("ns", 1);
            fileDidNotExist = true;
        }
        if (Files.exists(path)) {
            fileExists = true;
        }
        if ((fileExists) && (fileDidNotExist)) {
            check = true;
        }
        assertTrue(check);
        Files.delete(path);
    }

    @Test
    void writeWordsToExistingFile() throws FileNotFoundException, IOException {
        Task5.writeWordToExistingFile("пицца", 1);
        Path filePath = Path.of("пицца.txt");
        try (BufferedReader fromUnderdog = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine = fromUnderdog.readLine();
            assertEquals("пицца", currentLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void countWords() throws IOException {
        int count = 0;
        Task5.countWords("dataForTask5Test.txt", "countsTest.txt");
        String[] words = {"я", "хочу", "вкусной", "пиццы"};
        for (String current : words) {
            Path path = Path.of(current + ".txt");
            if (Files.exists(path)) {
                count++;
                Files.delete(path);
            }
        }
        assertEquals(4, count);
    }

    @Test
    void countWords2() {
        int numberOfRightLines = 0;
        Path filePath = Path.of("countsTest.txt");
        String[] words = {"пиццы=1", "хочу=1", "вкусной=1", "я=1"};
        try (BufferedReader fromCountsTestFile = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine;
            for (int i = 0; i < 4; i++) {
                currentLine = fromCountsTestFile.readLine();
                if (currentLine.equals(words[i])) {
                    numberOfRightLines++;
                }
            }
            assertEquals(4, numberOfRightLines);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}