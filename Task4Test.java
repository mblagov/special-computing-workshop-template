package ru.spbu.apcyb.svp.tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
/**
 * Тесты для задания 4.
 */
class Task4Test {
  public static final Logger logger = Logger.getLogger(Task4Test.class.getName());
  public static final String INPUT = "numbers.txt";
  public static final String OUT_SINGLE = "Single.txt";
  public static final String OUT_MULTI = "Multi.txt";

  @Test
  void generateNumbers() throws IOException {
    assertTrue(Task4.generateNums(10000, INPUT));
  }

  private static Stream < Arguments > provideExceptionInputs() throws IOException {
    FileWriter res = new FileWriter("Test.txt", false);
    res.close();
    FileNotFoundException thrown1 =
        assertThrows(FileNotFoundException.class, () -> Task4.tanSingle(100, res, INPUT));
    Tan tan = new Tan(1, 1, res, INPUT);
    FileNotFoundException thrown2 =
        assertThrows(FileNotFoundException.class, tan::tanToFile);
    return Stream.of(
        Arguments.of(thrown1, "Ошибка чтения файла в однопоточном режиме"),
        Arguments.of(thrown2, "Ошибка чтения из файла в многопоточном режиме"));
  }
  @ParameterizedTest
  @MethodSource("provideExceptionInputs")
  void fileNotFoundExceptionTest(FileNotFoundException actual, String expected) {
    assertEquals(expected, actual.getMessage());
  }
  @Test
  void singleThreadTest() throws IOException {
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
  void multiThreadTest() throws IOException {
    int numbers = 100;
    Task4.generateNums(numbers, INPUT);

    //Однопоток

    try (FileWriter outSingle = new FileWriter(OUT_SINGLE, false)) {
      long singleTime = Task4.tanSingle(numbers, outSingle, "numbers.txt");
    } catch (IOException e) {
      throw new IOException("Ошибка с файлом для вывода в многопоточном режиме");
    }
    BufferedReader readerSingle = new BufferedReader(new FileReader(OUT_SINGLE));
    List < String > expected = new ArrayList < > ();
    for (int i = 0; i < numbers; i++) {
      expected.add(readerSingle.readLine());
    }

    //Многопоток
    FileWriter writerMulti = new FileWriter(OUT_MULTI, false);
    Task4.tanThreaded(100, 10, writerMulti, INPUT);
    writerMulti.close();
    //Проверка
    int seen = 0;
    int checked = 0;
    try (BufferedReader readerMulti = new BufferedReader(new FileReader(OUT_MULTI))) {
      String line;
      while ((line = readerMulti.readLine()) != null) {
        seen++;
        if (expected.contains(line)) {
          checked++;
        }
      }
      assertEquals(seen, checked);
    } catch (IOException e) {
      throw new FileNotFoundException("Ошибка с чтением из файла multi.txt");
    }
  }
}