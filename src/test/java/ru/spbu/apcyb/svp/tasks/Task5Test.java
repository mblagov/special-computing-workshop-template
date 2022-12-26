package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
class Task5Test {

  /**
   * Проверка на создание нового файла.
   */
  @Test
  void createFileTest() throws IOException {
    boolean absent = false;
    boolean exists = false;
    boolean check = false;
    Path path = Path.of(Task5.PATH + "nonExistent" + ".txt");
    if (!Files.exists(path)) {
      Task5.createFile("nonExistent", 1);
      absent = true;
    }
    if (Files.exists(path)) {
      exists = true;
    }
    if ((exists) && (absent)) {
      check = true;
    }
    assertTrue(check);
    Files.delete(path);
  }

  /**
   * Проверка на запись слова в файл.
   */
  @Test
  void writeWordsTest() throws FileNotFoundException {
    String expected = "newWord";
    Task5.writeWords(expected, 1);
    try (BufferedReader reader = new BufferedReader(
        new FileReader(Task5.PATH + expected + ".txt"))) {
      String actual = reader.readLine();
      assertEquals(expected, actual);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Проверка на создание отдельных файлов для каждого слова из книги.
   */
  @Test
  void processBookCreationTest() throws IOException {
    int actual = 0;
    Task5.processBook(Task5.RESOURCES + "bookTest1.txt", Task5.RESOURCES + "countsTest1.txt");
    List<String> words = List.of("five", "six", "seven");
    for (String word : words) {
      Path path = Path.of(Task5.PATH + word + ".txt");
      if (Files.exists(path)) {
        actual++;
        Files.delete(path);
      } else {
        Task5.logger.info("Проблема со словом \"" + word + "\"");
      }
    }
    assertEquals(words.size(), actual);
  }

  /**
   * Проверка правильности данных в файле counts.txt.
   */
  @Test
  void processBookOutputTest() throws IOException {
    int actual = 0;
    List<String> words = List.of("one=2", "two=1", "three=1");
    Task5.processBook(Task5.RESOURCES + "bookTest2.txt", Task5.RESOURCES + "countsTest2.txt");
    try (BufferedReader reader = new BufferedReader(
        new FileReader(Task5.RESOURCES + "countsTest2.txt"))) {
      String line;
      for (int i = 0; i < words.size(); i++) {
        line = reader.readLine();
        if (words.contains(line)) {
          actual++;
        } else {
          Task5.logger.info("Проблема со словом: " + line);
        }
      }
      assertEquals(words.size(), actual);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
