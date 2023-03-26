package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  void test1() throws IOException {
    FileWriter resFile = new FileWriter("res1.txt", false);
    FileNotFoundException thrown =
        assertThrows(FileNotFoundException.class, () -> Task3.searchFiles(resFile, "....."));
    assertEquals("java.io.FileNotFoundException", thrown.toString());
  }

  @Test
  void test2() throws IOException {
    FileWriter resFile = new FileWriter("res2.txt", false);
    boolean result = Task3.searchFiles(resFile, "..//");
    assertTrue(result);
  }
}