package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  /**
   Метод, высчитывающий тангенс в данной строке.
   */
  public static Double tanToCurrentLine(int numberOfCurrentLine) throws FileNotFoundException {
    double result;
    try (BufferedReader br = new BufferedReader(new FileReader("task4.txt"))) {
      String currentLine;
      for (int i = 0; i < numberOfCurrentLine - 1; i++) {
        currentLine = br.readLine();
      }
      currentLine = br.readLine();
      result = Math.tan(Double.parseDouble(currentLine));
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл task4.txt не найден.");
    }
    return result;
  }

  /**
   Метод, высчитывающий в многопоточном режиме тангенсы данных NumberOfLines строк.
   */
  public static void multiThread(
      FileWriter multiThreadWriter, int numberOfLines, int numberOfThreads)
      throws ExecutionException, InterruptedException, IOException {
    ThreadPool threadPool = new ThreadPool(numberOfThreads);
    Logger logger = Logger.getLogger(Task4.class.getName());
    long start = System.nanoTime();

    List<Future<Double>> futures = new ArrayList<>();
    for (int i = 0; i < numberOfLines / numberOfThreads; i++) {
      for (int j = 1; j <= numberOfThreads; j++) {
        final int k = j + i * numberOfThreads;
        futures.add(
            CompletableFuture.supplyAsync(
                () -> {
                  try {
                    return tanToCurrentLine(k);
                  } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                  }
                },
                threadPool
            ));
      }
    }

    for (Future<Double> future : futures) {
      multiThreadWriter.write(String.valueOf(future.get()));
      multiThreadWriter.append("\n");
    }

    String executedTime = String.format("Время работы программы в однопоточном режиме "
        + "см. Multi.txt", (System.nanoTime() - start));
    logger.info(executedTime);

    threadPool.shutdown();
  }

  /**
   Метод, высчитывающий в однопоточном режиме тангенсы данных NumberOfLines строк.
   */
  public static void singleThread(
      FileWriter singleThreadWriter, int numberOfLines) throws FileNotFoundException {
    Logger logger = Logger.getLogger(Task4.class.getName());
    Long start = System.nanoTime();
    try (BufferedReader br = new BufferedReader(new FileReader("task4.txt"))) {
      String currentLine;
      int tmp = 0;
      while ((currentLine = br.readLine()) != null || tmp < numberOfLines) {
        singleThreadWriter.write(String.valueOf(Math.tan(Double.parseDouble(currentLine))));
        singleThreadWriter.append("\n");
        tmp++;
      }
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл task4.txt не найден.");
    }

    String executedTime = String.format("Время работы программы в однопоточном режиме "
            + "см. Sigle.txt", (System.nanoTime() - start));
    logger.info(executedTime);
  }


  /**
   * Main.
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    FileWriter multiThreadResFile = new FileWriter("Multi.txt", false);
    multiThread(multiThreadResFile, 1000, 10);
    FileWriter singleThreadResFile = new FileWriter("Single.txt", false);
    singleThread(singleThreadResFile, 1000);
  }
}
