package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 5.
 */
class Task5Test {

  @Test
  void normalRun() {
    assertDoesNotThrow(
        () -> Task5.streamProccessWords("text", "numbers")
    );
  }

  @Test
  void inFileInIncorrectInput() {
    assertThrows(
        FileNotFoundException.class,
        () -> Task5.streamProccessWords("", "/a/b/c/d/e/f")
    );
  }

  @Test
  void outFileInInexistingFolder() {
    assertThrows(
        FileNotFoundException.class,
        () -> Task5.streamProccessWords("text", "/a/b/c/d/e/f")
    );
  }

  @Test
  void correctWrite2File() throws FileNotFoundException {
    Task5.writeWords2FIle("abcdefgh", (long)1);
    try (BufferedReader readBuffer = new BufferedReader(new FileReader("abcdefgh"))) {
      String currentLine = readBuffer.readLine();
      assertEquals("abcdefgh", currentLine);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void correctWordsProcess() throws IOException {
    String[] words = {"a", "aa"};

    for (String fileName : words) {
      File file = new File(fileName);
      if (file.exists()) {
        file.delete();
      }
    }

    Task5.streamProccessWords("text", "counts");
    int count = 0;
    for (String fileName : words) {
      File file = new File(fileName);
      if (file.exists()) {
        count++;
        file.delete();
      }
    }
    assertEquals(2, count);
  }

}