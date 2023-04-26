package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Тесты для задания 4. */
class Task4Test {
  public static final Logger logger = Logger.getLogger(Task4Test.class.getName());
  public static final String INPUT = "numbers.txt";
  public static final String OUT_SINGLE = "Single.txt";

  @Test
  void generateNumbers() throws IOException {
    assertTrue(Task4.generateNums(10000, INPUT));
  }

  @Test
  void readToBuffer() throws IOException {
    Task4 testClass = new Task4(10);
    try (BufferedReader testReader = new BufferedReader(new FileReader("test1.txt"))) {
      testClass.readToBuffer(testReader);
      double[] exp = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 8, 8};
      double[] act = testClass.getBuffer();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("test error");
    }
  }

  void writeForTest() throws IOException {
    Task4 test = new Task4(1);
    ExecutorService testExecutor = Executors.newSingleThreadExecutor();
    Future<Double>[] testInput = new Future[1];
    testInput[0] = testExecutor.submit(new Tan(0));
    try (FileWriter testWriter = new FileWriter("test4.txt")) {
      test.writeToFile(testWriter, testInput);
    } catch (IOException | ExecutionException | InterruptedException e) {
      throw new IOException("writeForTest error");
    }
  }

  @Test
  void writeToFileTest() throws IOException {
    writeForTest();
    try (BufferedReader expReader = new BufferedReader(new FileReader("test3.txt"));
        BufferedReader actReader = new BufferedReader(new FileReader("test2.txt"))) {
      Assertions.assertEquals(
          Double.parseDouble(expReader.readLine()), Double.parseDouble(actReader.readLine()));
    } catch (IOException e) {
      throw new IOException("writeToFileTest error");
    }
  }

  @Test
  void singleTest() throws IOException {
    int numbers = 100;
    Task4.generateNums(numbers, INPUT);
    try (FileWriter outSingle = new FileWriter(OUT_SINGLE, false)) {
      long singleTime = Task4.tanSingle(numbers, outSingle, "numbers.txt");
    } catch (IOException e) {
      throw new IOException("Ошибка с файлом для вывода в многопоточном режиме");
    }
    BufferedReader inputReader = new BufferedReader(new FileReader(INPUT));
    BufferedReader singleReader = new BufferedReader(new FileReader(OUT_SINGLE));
    String currentLine;
    String res;
    boolean check = true;
    for (int i = 0; i < numbers; i++) {
      currentLine = singleReader.readLine();
      res = inputReader.readLine();
      res = Double.toString(Math.tan(Double.parseDouble((res))));
      if (!currentLine.equals(res)) {
        logger.info("Ошибка в строке " + res + "!\n");
        check = false;
      }
      assertTrue(check);
    }
  }

  @Test
  void multiTest() throws IOException {
    Task4 test = new Task4(10);
    test.tanThreaded("testin.txt", "testout.txt");
    try (BufferedReader expReader = new BufferedReader(new FileReader("test3.txt"));
        BufferedReader actReader = new BufferedReader(new FileReader("testout.txt"))) {
      String[] exp = new String[2];
      exp[0] = expReader.readLine();
      exp[1] = expReader.readLine();
      String[] act = new String[2];
      act[0] = actReader.readLine();
      act[1] = actReader.readLine();
      Assertions.assertArrayEquals(exp, act);
    } catch (IOException e) {
      throw new IOException("Multitest error");
    }
  }
}
