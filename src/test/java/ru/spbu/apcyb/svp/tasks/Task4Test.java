package ru.spbu.apcyb.svp.tasks;

/**
 * Тесты для задания 4.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Task4Test {

  private static final String outputPath =
      ".\\src\\test\\resources\\num4\\output.txt"; //Выводы всех запросов
  private static final String resultsPath =
      ".\\src\\test\\resources\\num4\\results.txt"; //Время выполнения для запросов
  private static Task4 proc;

  @BeforeAll
  static void init() {
    try {
      new PrintWriter(resultsPath).close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      new PrintWriter(outputPath).close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      //Случайные double числа
      String inputPath = ".\\src\\test\\resources\\num4\\input.txt";
      proc = new Task4(inputPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void checkInitializer() {
    assertEquals(proc.array.get(0), 2.44582);
  }

  @Test
  public void checkCalculateMethod() {
    try {
      assertEquals(proc.calculate(1),
          "1 numbers were processed\nval = 2.44582; tan(val) = " + Math.tan(2.44582));
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void checkMainMethod() {
    int[] inquiries = {1, 100, 1000000};    //Запросы: количество обрабатываемых чисел
    for (int inquiry : inquiries) {
      Long startTime = System.nanoTime();
      String calc = null;
      try {
        assert proc != null;
        calc = proc.calculate(inquiry);
      } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
      }
      Long stopTime = System.nanoTime();
      try {
        Files.write(Paths.get(resultsPath),
            Collections.singleton("For numbers = " + inquiry + "\nExecution time: "
                + (double) (stopTime - startTime) / 1000000 + " milli"), StandardOpenOption.APPEND);
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        Files.write(Paths.get(outputPath), Collections.singleton(calc), StandardOpenOption.APPEND);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}