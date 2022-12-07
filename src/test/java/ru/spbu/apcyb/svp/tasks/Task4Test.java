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

  /**
   * В тесте проводится проверка на наличие файла, со строк которого будут высчитываться тангенсы.
   */
  @Test
  void testNotExistingFile() throws IOException {
    FileWriter singleThreadResFile = new FileWriter("con.txt", false);
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task4.singleThread(singleThreadResFile, 100));
    assertEquals("Файл text.txt не найден.", thrown.getMessage());
  }

  /**
   * В тесте проводится проверка на обработку из файла, при этом передаем директорию.
   */
  @Test
  void testNotFile() throws IOException {
    try {
      FileWriter singleThreadResFile = new FileWriter("text1.txt", false);
    } catch (FileNotFoundException e) {
      assertEquals("text1.txt (Is a directory)", e.getMessage());
    }
  }

  /**
   * В тесте проводится вычисление тангенса первых 1000 строк кода и сравнение результатов с результатами выполнения метода singleThread.
   */
  @Test
  void testSingleThread() throws IOException {
    FileWriter singleThreadResFile = new FileWriter("SingleRes.txt", false);
    Task4.singleThread(singleThreadResFile, 1000);
    BufferedReader textBr = new BufferedReader(new FileReader("text.txt"));
    BufferedReader singleResBr = new BufferedReader(new FileReader("SingleRes.txt"));
    boolean result = true;
    for (int i = 0; i < 1000; i++) {
      if (Math.tan(Double.parseDouble(textBr.readLine())) != Double.parseDouble(singleResBr.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }

  /**
   * В тесте проводится вычисление тангенса первых 100 строк кода и сравнение результатов с результатами выполнения метода multiThread.
   */
  @Test
  void testMultiThread() throws IOException, ExecutionException, InterruptedException {
    FileWriter multiThreadResFile = new FileWriter("MultiRes.txt", false);
    Task4.multiThread(multiThreadResFile, 1000, 10);
    BufferedReader textBr = new BufferedReader(new FileReader("text.txt"));
    BufferedReader multiResBr = new BufferedReader(new FileReader("MultiRes.txt"));
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
