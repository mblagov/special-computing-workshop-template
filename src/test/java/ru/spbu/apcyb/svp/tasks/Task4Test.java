package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;


/**
 * Тесты для задания 4.
 */
class Task4Test {

  @Test
  void testNotExistingFile() throws IOException {
    FileWriter singleThreadResFile = new FileWriter("con.txt", false);
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class,
        () -> Task4.singleThread(singleThreadResFile, 100));
    assertEquals("Файл task4.txt не найден.", thrown.getMessage());
  }

  @Test
  void testNotFile() throws IOException {
    try {
      FileWriter singleThreadResFile = new FileWriter("test1.txt", false);
    } catch (FileNotFoundException e) {
      assertEquals("test1.txt (Is a directory)", e.getMessage());
    }
  }

  @Test
  void testSingleThread() throws IOException {
    FileWriter singleThreadResFile = new FileWriter("Single.txt", false);
    Task4.singleThread(singleThreadResFile, 1000);
    BufferedReader textBr = new BufferedReader(new FileReader("task4.txt"));
    BufferedReader singleResBr = new BufferedReader(new FileReader("Single.txt"));
    boolean result = true;
    for (int i = 0; i < 1000; i++) {
      if (Math.tan(Double.parseDouble(textBr.readLine())) != Double.parseDouble(singleResBr.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }

  @Test
  void testMultiThread() throws IOException, ExecutionException, InterruptedException {
    FileWriter multiThreadResFile = new FileWriter("Multi.txt", false);
    Task4.multiThread(multiThreadResFile, 1000, 10);
    BufferedReader textBr = new BufferedReader(new FileReader("task4.txt"));
    BufferedReader multiResBr = new BufferedReader(new FileReader("Multi.txt"));
    boolean result = true;
    for (int i = 0; i < 100; i++) {
      if (Math.tan(Double.parseDouble(textBr.readLine())) != Double.parseDouble(multiResBr.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }
}