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
  public static final String INPUT = "./src/main/resources/Task4/inputs.txt";
  public static final String OUT_SINGLE = "./src/main/resources/Task4/single.txt";
  public static final String OUT_MULTI = "./src/main/resources/Task4/multi.txt";

  /**
   * Тестируем генератор чисел.
   */
  @Test
  void generatorTest() throws IOException {
    int numbers = 100;
    assertTrue(Task4.generateNumbers(numbers));
  }

  /**
   * Тестируем метод с однопоточным вычислением на 100 числах.
   */
  @Test
  void singleThreadTest() throws IOException {
    int numbers = 100;
    Task4.generateNumbers(numbers);
    FileWriter outSingle = new FileWriter(OUT_SINGLE, false);
    Task4.singleThread(INPUT, outSingle, numbers);
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

  /**
   * 1) Сначала генерируем случайные числа и проводим вычисления в однопотоке. 2) Заносим числа из
   * однопоточного файла в List expected. 3) Проводим многопоточное вычисление. 4) Проверяем
   * вхождение чисел из многопоточного файла в List expected.
   */
  @Test
  void multiThreadTest() throws IOException {
    //Генерируем числа
    int numbers = 100;
    Task4.generateNumbers(numbers);
    //Однопоток
    FileWriter outSingle = new FileWriter(OUT_SINGLE, false);
    Task4.singleThread(INPUT, outSingle, numbers);
    BufferedReader readerSingle = new BufferedReader(new FileReader(OUT_SINGLE));
    List<String> expected = new ArrayList<>();
    for (int i = 0; i < numbers; i++) {
      expected.add(readerSingle.readLine());
    }
    //Многопоток
    FileWriter writerMulti = new FileWriter(OUT_MULTI, false);
    Task4.multiThread(INPUT, writerMulti, 100, 10);
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

  /**
   * Функция предоставления аргументов для тестов.
   *
   * @return поток аргументов.
   */
  private static Stream<Arguments> provideExceptionInputs() throws IOException {
    FileWriter res = new FileWriter("./src/main/resources/Task4/Test.txt", false);
    res.close();
    FileNotFoundException thrown1 =
        assertThrows(FileNotFoundException.class, () -> Task4.singleThread(INPUT, res, 100));
    Tan tan = new Tan(1, 1, res, INPUT);
    FileNotFoundException thrown2 =
        assertThrows(FileNotFoundException.class, tan::tanToFile);
    return Stream.of(
        //Попытка записи в существующий файл
        Arguments.of(thrown1, "Ошибка чтения из файла в однопотоке"),
        //Попытка записи в несуществующий файл
        Arguments.of(thrown2, "Ошибка чтения из файла в многопотоке"));
  }

  /**
   * Проверка на исключения.
   *
   * @param actual   пойманное исключение.
   * @param expected ожидаемое сообщение.
   */
  @ParameterizedTest
  @MethodSource("provideExceptionInputs")
  void fileNotFoundExceptionTest(FileNotFoundException actual, String expected) {
    assertEquals(expected, actual.getMessage());
  }
}
