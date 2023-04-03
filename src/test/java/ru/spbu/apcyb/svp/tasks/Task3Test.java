package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  void test1() {
    File resFile = new File("res1.txt");
    FileNotFoundException thrown =
        assertThrows(FileNotFoundException.class, () -> Task3.searchFiles(resFile, "....."));
    assertEquals("java.io.FileNotFoundException: Файл не существует", thrown.toString());
  }

  @Test
  void test2() throws IOException {
    File resFile = new File("res2.txt");
    boolean result = Task3.searchFiles(resFile, "..//");
    assertTrue(result);
  }

  @Test
  void test3() throws IOException {
    File resFile = new File("res3.txt");
    Task3.searchFiles(resFile, "..//");
    boolean result = (resFile.length() != 0);
    assertTrue(result);
  }
}