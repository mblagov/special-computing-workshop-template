package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */

class Task5Test {

  @Test
  void wordsToNonExistingFile() throws IOException {
    boolean fileDidNotExist = false;
    boolean fileExists = false;
    boolean check = false;
    Path path =
        Path.of("test1" + ".txt");
    if (!Files.exists(path)) {
      Task5.wordsToNonExistingFile("test1", 1);
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
  void wordsToExistingFile() throws FileNotFoundException {
    Task5.wordsToExistingFile("test2", 1);
    Path filePath = Path.of("test2.txt");
    try (BufferedReader fromTest2 = new BufferedReader(new FileReader(filePath.toFile()))) {
      String currentLine = fromTest2.readLine();
      assertEquals("test2", currentLine);
    } catch (IOException e) {
      throw new RuntimeException("Проблема при записи в существующий файл");
    }
  }

  @Test
  void textFileSort() throws IOException {
    int count = 0;
    Task5.sortFile("TestTask5.txt", "countsTestFile.txt");
    String[] words = {"aaa", "bbb"};
    for (String word : words) {
      Path path = Path.of(word + ".txt");
      if (Files.exists(path)) {
        count++;
        Files.delete(path);
      }
    }
    assertEquals(4, count);
  }

  @Test
  void textFileSort2() {
    int numberOfRightLines = 0;
    Path filePath = Path.of("countsTestFile");
    String[] words = {"word1=1", "word2=1", "word3=1", "word4=1"};
    try (BufferedReader fromCountsTestFile = new BufferedReader(
        new FileReader(filePath.toFile()))) {
      String currentLine;
      for (int i = 0; i < 4; i++) {
        currentLine = fromCountsTestFile.readLine();
        if (currentLine.equals(words[i])) {
          numberOfRightLines++;
        }
      }
      assertEquals(4, numberOfRightLines);
    } catch (IOException ex) {
      throw new RuntimeException("Проблема при чтении файла");
    }
  }
}
