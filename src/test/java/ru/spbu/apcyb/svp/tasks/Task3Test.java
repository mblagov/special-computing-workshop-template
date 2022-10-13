package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  void testEmptyInput() throws IOException {
    String[] args = new String[2];
    args[0] = "";
    args[1] = "answer.txt";
    try (FileWriter writer = new FileWriter(args[1], false)) {
      Task3.walk(args[0], writer, 0);
    } catch (FileNotFoundException thrown) {
      assertEquals("java.io.FileNotFoundException", thrown.toString());
    }
  }

  @Test
  void testInput() throws IOException {
    String[] args = new String[2];
    args[0] = "src/main";
    args[1] = "answer.txt";
    try (FileWriter writer = new FileWriter(args[1], false)) {
      Task3.walk(args[0], writer, 0);
    } catch (FileNotFoundException thrown) {
      assertEquals("java.io.FileNotFoundException", thrown.toString());
    }
  }
}
