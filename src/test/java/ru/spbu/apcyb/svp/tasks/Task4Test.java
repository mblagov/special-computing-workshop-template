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
  void randomNumbersTest1() throws IOException {

    boolean result = Task4.randomNumbers(1000);

    assertTrue(result);
  }

  @Test
  void SingleExceptionTest() {

    FileNotFoundException thrown = assertThrows(FileNotFoundException.class,
        () -> Task4.SingleThreadTan(new FileWriter("err.txt", false), 100));
    assertEquals("Файл с числами не найден.", thrown.getMessage());
  }

  @Test
  void ExceptionTest() throws IOException {

    try {
      FileWriter fw = new FileWriter("err1.txt", false);
    } catch (FileNotFoundException e) {
      assertEquals("err1.txt (Is a directory)", e.getMessage());
    }
  }

  @Test
  void SingleThreadTest() throws IOException {
    Task4.SingleThreadTan(new FileWriter("SingleThreads.txt", false), 1000);
    BufferedReader numbersFile = new BufferedReader(new FileReader("numbers.txt"));
    BufferedReader singleFile = new BufferedReader(new FileReader("SingleThread.txt"));
    boolean result = true;
    for (int i = 0; i < 100; i++) {
      if (Math.tan(Double.parseDouble(numbersFile.readLine())) != Double.parseDouble(
          singleFile.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }

  @Test
  void MultiThreadTest() throws IOException, ExecutionException, InterruptedException {
    Task4.MultiThreadTan(new FileWriter("MultiTan.txt", false), 1000, 10);
    BufferedReader numbersFile = new BufferedReader(new FileReader("numbers.txt"));
    BufferedReader multiFile = new BufferedReader(new FileReader("MultiThread.txt"));
    boolean result = true;
    for (int i = 0; i < 100; i++) {
      if (Math.tan(Double.parseDouble(numbersFile.readLine())) != Double.parseDouble(
          multiFile.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }

  @Test
  void isCompareWork() throws IOException, ExecutionException, InterruptedException {
    assertTrue(Task4.Compare(1000, 10));
  }

}
