package ru.spbu.apcyb.svp.tasks.task4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  /**
   * основной метод программы.
   *
   * @param args
   *     параметры
   *
   * @throws IOException
   *
   * @throws ExecutionException
   *
   * @throws InterruptedException
   *
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    String numbersFile = "./src/main/resources/numbersGen.txt";
    String singleAnswerFile = "./src/main/resources/tanAnsSingle.txt";
    String multiAnswerFile = "./src/main/resources/tanAnsMulti.txt";
    int threadCount = 10;

    NumberGeneratorUtility.generateSequence(numbersFile, 100, threadCount);

    countTan(numbersFile, singleAnswerFile, multiAnswerFile, threadCount);
  }

  /**
   * метод рассчета тангенса чисел из файла двумя способами.
   *
   * @param numbersFile
   *     путь к файлу с числами
   * @param singleAnswerFile
   *     путь к файлу с ответами для одного потока
   * @param multiAnswerFile
   *     путь к файлу с ответами для многопоточного случая
   * @param threadCount
   *     количество потоков
   * @throws IOException
   *
   * @throws ExecutionException
   *
   * @throws InterruptedException
   *
   */

  public static void countTan(
      String numbersFile, String singleAnswerFile, String multiAnswerFile, int threadCount)
      throws IOException, ExecutionException, InterruptedException {
    var logger = Logger.getLogger("");

    var sStart = System.currentTimeMillis();
    countTanSingle(numbersFile, singleAnswerFile);
    var sEnd = System.currentTimeMillis();
    logger.log(Level.INFO, () -> "Singlethread time: " + (sEnd - sStart));

    var mTimeStart = System.currentTimeMillis();
    countTanMulti(numbersFile, multiAnswerFile, threadCount);
    var mTimeEnd = System.currentTimeMillis();
    logger.log(Level.INFO, () -> "Multithread time: " + (mTimeEnd - mTimeStart));
  }

  private static void countTanSingle(String numbersFile, String singleAnswerFile)
      throws IOException {
    try (var sc = new Scanner(new FileReader(numbersFile)).useLocale(Locale.US)) {
      try (var fileWriter = new FileWriter(singleAnswerFile)) {
        while (sc.hasNextDouble()) {
          fileWriter.write(Math.tan(sc.nextDouble()) + "\n");
        }
        fileWriter.flush();
      }
    }
  }

  private static String countTanForOneLine(String filename, int numberOfLine)
      throws FileNotFoundException {
    try (var sc = new Scanner(new FileReader(filename))) {
      for (int i = 0; i < numberOfLine - 1; i++) {
        sc.nextLine();
      }
      try (var sc2 = new Scanner(sc.nextLine()).useLocale(Locale.US)) {
        var answer = new StringBuilder();
        while (sc2.hasNextDouble()) {
          answer.append(Math.tan(sc2.nextDouble()) + "\n");
        }
        return answer.toString();
      }
    }
  }

  private static void countTanMulti(String numbersFile, String multiAnswerFile, int threadCount)
      throws IOException, ExecutionException, InterruptedException {
    var exService = Executors.newFixedThreadPool(threadCount);
    Callable<String>[] tasks = new Callable[threadCount];
    Future<String>[] futures = new Future[threadCount];
    for (int i = 1; i <= threadCount; i++) {
      final int number = i;
      tasks[i - 1] = () -> countTanForOneLine(numbersFile, number);
      futures[i - 1] = exService.submit(tasks[i - 1]);
    }
    try (var fileWriter = new FileWriter(multiAnswerFile)) {
      for (var future : futures) {
        fileWriter.write(future.get());
      }
      fileWriter.flush();
    }
  }
}
