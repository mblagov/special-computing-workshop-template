package ru.spbu.apcyb.svp.tasks;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * Тесты для задания 5.
 */
class Task5Test {

  @Test
  void wordsSort() throws IOException {

    int count = 0;
    String[] words = {"foo", "bar", "baz"};
    Task5.wordsSort("task5.txt", "countsTest.txt");
    for (String current : words) {
      Path path = Path.of(current + ".txt");
      if (Files.exists(path)) {
        count++;
      }
    }
    assertEquals(3, count);
  }

  @Test
  void wordsSort2() {
    int wordLines = 0;
    String thisLine;
    String[] words = {"foo=2", "bar=1", "baz=1"};
    Path filePath = Path.of("countsTest");
    try (BufferedReader fromCountsTestFile = new BufferedReader(
        new FileReader(filePath.toFile()))) {
      for (int i = 0; i < 4; i++) {
        thisLine = fromCountsTestFile.readLine();
        if (thisLine.equals(words[i])) {
          wordLines++;
        }
      }
      assertEquals(3, wordLines);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  void writeWordsToFile() throws FileNotFoundException {
    Task5.writeWordsToFile("foo", 2);
    Path filePath = Path.of("foo.txt");
    try (BufferedReader fromFoo = new BufferedReader(new FileReader(filePath.toFile()))) {
      String thisLine = fromFoo.readLine();
      assertEquals("foo", thisLine);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  void writeWordsToNewFile() throws IOException {
    boolean check = false;
    boolean fileExists = false;
    boolean fileNotExist = false;
    Path path = Path.of("baz" + ".txt");
    if (!Files.exists(path)) {
      Task5.writeWordsToNewFile("baz", 1);
      fileNotExist = true;
    }
    if (Files.exists(path)) {
      fileExists = true;
    }
    if ((fileExists) && (fileNotExist)) {
      check = true;
    }
    assertTrue(check);
  }
}
