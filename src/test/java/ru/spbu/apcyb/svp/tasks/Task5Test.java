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

    /**
     * Проверяет, есть ли файл с таким названием.
     * Если нет, то запускает метод.
     * Проверяет, что файл появился.
     */
    @Test
    void wordsToNonExistingFile() throws IOException {
        boolean fileDidNotExist = false;
        boolean fileExists = false;
        boolean check = false;
        String[] word = {"toyotaravfour", "1"};
        Path path =
                Path.of(word[0] + ".txt");
        if (!Files.exists(path)) {
            Task5.wordsToNonExistingFile(word);
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

    /**
     * Проверяет, вывелось ли слово в нужный файл.
     */
    @Test
    void wordsToExistingFile() throws FileNotFoundException {
        String[] testWord = {"underdog", "1"};
        Task5.wordsToExistingFile(testWord);
        Path filePath = Path.of("underdog.txt");
        try (BufferedReader fromUnderdog = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine = fromUnderdog.readLine();
            assertEquals("underdog", currentLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Проверяет, создались ли файлы под слова.
     */
    @Test
    void textFileSort() throws IOException {
        int count = 0;
        Task5.textFileSort("task5ReferenceTestFile.txt", "countsTestFile.txt");
        String[] words = {"underdog", "supercat", "toyotaravfour", "huperman"};
        for (String current : words) {
            Path path = Path.of(current + ".txt");
            if (Files.exists(path)) {
                count++;
                Files.delete(path);
            }
        }
        assertEquals(4, count);
    }

    /**
     * Проверяет, правильные ли данные оказались в файле результата.
     */
    @Test
    void textFileSort2() {
        int numberOfRightLines = 0;
        Path filePath = Path.of("countsTestFile");
        String[] words = {"underdog=1", "supercat=1", "toyotaravfour=1", "huperman=1"};
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

    /**
     * Проверяет ошибку, при чтении исходного текста из несуществующего файла.
     * Или при выводе результата в несуществующий файл
     */
    @Test
    void textFileSortEx() {
        FileNotFoundException thrown =
                assertThrows(FileNotFoundException.class, () -> Task5.textFileSort("NonExistingFile.txt", "countsTestFile.txt"));
        assertEquals("problems with NonExistingFile.txt or countsTestFile.txt file", thrown.getMessage());
    }
}
