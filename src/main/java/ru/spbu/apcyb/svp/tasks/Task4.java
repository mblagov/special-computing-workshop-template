package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  public static final Logger logger = Logger.getLogger(Task4.class.getName());
  public static final String INPUT = "./src/main/resources/Task4/inputs.txt";
  public static final String OUT_SINGLE = "./src/main/resources/Task4/single.txt";
  public static final String OUT_MULTI = "./src/main/resources/Task4/multi.txt";
  public static final Integer NUMBERS = 100000;
  public static final Integer THREADS = 10;

  /**
   * Генерирует последовательность чисел типа double в диапазоне [-1.5;1.5] для адекватного значения
   * тангенсов.
   *
   * @param numbers количество генерируемых чисел.
   * @return true в случае успеха, false в иных случаях.
   */
  public static boolean generateNumbers(Integer numbers) throws IOException {
    Random rand;
    try {
      rand = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      logger.info("Не удалось получить хорошую последовательность псевдослучайных чисел");
      return false;
    }
    try (FileWriter writer = new FileWriter(INPUT, false)) {
      for (int i = 0; i < numbers; i++) {
        double random = rand.nextDouble() * 3 - 1.5;
        writer.write(random + "\n");
      }
      logger.info("Случайные числа сгенерированы");
      return true;
    } catch (IOException e) {
      throw new IOException("Ошибка с записью чисел в inputs.txt");
    }
  }

  /**
   * Вычисления тангенсов в однопотоке.
   *
   * @param input   путь к файлу для чтения в формате строки.
   * @param writer  файл для вывода.
   * @param numbers количество чисел.
   */
  public static void singleThread(String input, FileWriter writer, Integer numbers)
      throws IOException {
    long time = System.currentTimeMillis();
    try (writer; BufferedReader reader = new BufferedReader(new FileReader(input))) {
      String line;
      for (int i = 0; i < numbers; i++) {
        line = reader.readLine();
        writer.write(Math.tan(Double.parseDouble(line)) + "\n");
      }
      String message = System.currentTimeMillis() - time
          + " - время в однопотоке для " + numbers + " тангенсов";
      logger.info(message);
    } catch (IOException e) {
      throw new FileNotFoundException("Ошибка чтения из файла в однопотоке");
    }
  }

  /**
   * Вычисления в многопотоке.
   *
   * @param input   файл для чтения в формате строки.
   * @param writer  файл для записи.
   * @param numbers количество чисел в файле input.
   * @param threads количество потоков.
   */
  public static void multiThread(String input, FileWriter writer, Integer numbers,
      Integer threads) {
    long time = System.currentTimeMillis();
    List<Tan> tans = new ArrayList<>();
    int numbersPerThread = numbers / threads;
    try {
      for (int i = 0; i < threads; i++) {
        Tan tan = new Tan(i * numbersPerThread + 1, numbersPerThread, writer, input);
        tans.add(tan);
        tan.start();
      }
    } finally {
      try {
        for (Tan tan : tans) {
          tan.join();
        }
      } catch (InterruptedException e) {
        logger.warning("Interrupted!" + e);
        Thread.currentThread().interrupt();
      }
    }

    String message = System.currentTimeMillis() - time
        + " - время в многопотоке(" + threads + ") для " + numbers + " тангенсов";
    logger.info(message);
  }

  /**
   * Просто мейн.
   */
  public static void main(String[] args) throws IOException {
    boolean ok = generateNumbers(NUMBERS);
    if (ok) {
      try (FileWriter outSingle = new FileWriter(OUT_SINGLE, false)) {
        singleThread(INPUT, outSingle, NUMBERS);
      } catch (IOException e) {
        throw new IOException("Ошибка с файлом для вывода в однопотоке");
      }
      try (FileWriter outMulti = new FileWriter(OUT_MULTI, false)) {
        multiThread(INPUT, outMulti, NUMBERS, THREADS);
      } catch (IOException e) {
        throw new IOException("Ошибка с файлом для вывода в многопотоке");
      }
    } else {
      logger.info("Случайные числа не посчитались");
    }
  }
}
