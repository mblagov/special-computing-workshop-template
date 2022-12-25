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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Задание 4.
 */
public class Task4 {
  private static final Logger logger = LogManager.getLogger(Task4.class);

  /** Метод вычисления tg.
   *
   * @param numbOfLine - номер строки
   * @return значение tg
   * @throws FileNotFoundException - если файл был не найден
   */
  public static Double tanFromFileLine(int numbOfLine) throws FileNotFoundException {

    try (BufferedReader br = new BufferedReader(new FileReader("x.txt"))) {
      String line = "";

      for (int i = 0; i < numbOfLine; i++) {
        line = br.readLine();
      }
      return Math.tan(Double.parseDouble(line));
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл.txt не был найден.");
    }
  }

  /** Метод вычисления tg в многопоточном режиме.
   *
   * @param fw - fileWriter для "MultiThread.txt"
   * @param numbOfLines - количество строк для вычисления
   * @param numbOfThreads - количество потоков
   * @return время работы
   * @throws ExecutionException если не получается получить значение из future
   * @throws InterruptedException если было прервано получение значения из future
   * @throws IOException если не удалось открыть файл
   */
  public static long multiThread(FileWriter fw, int numbOfLines, int numbOfThreads)
      throws ExecutionException, InterruptedException, IOException {

    final long start = System.nanoTime();
    TanThread tanThread = new TanThread(numbOfThreads);
    List<Future<Double>> futures = new ArrayList<>();

    for (int i = 0; i < numbOfLines / numbOfThreads; i++) {
      for (int j = 1; j <= numbOfThreads; j++) {

        final int k = j + i * numbOfThreads;

        futures.add(
            CompletableFuture.supplyAsync(
                () -> {
                  try {
                    return tanFromFileLine(k);
                  } catch (Exception e) {
                    throw new RuntimeException(e);
                  }
                },
                tanThread
            ));
      }
    }

    for (Future<Double> future : futures) {
      fw.write(String.valueOf(future.get()));
      fw.append("\n");
    }
    tanThread.shutdown();
    return (System.nanoTime() - start);
  }

  /** Метод вычисления tg в однопоточном режиме.
   *
   * @param fw - fileWriter для "SingleThread.txt"
   * @param numbOfLines - количество строк для вычисления
   * @return время работы
   * @throws FileNotFoundException если файл не был найден
   */
  public static long singleThread(FileWriter fw, int numbOfLines) throws FileNotFoundException {

    long start = System.nanoTime();

    try (BufferedReader br = new BufferedReader(new FileReader("x.txt"))) {

      String line;
      long helper = 0;

      while ((line = br.readLine()) != null && helper < numbOfLines) {

        fw.write(String.valueOf(Math.tan(Double.parseDouble(line))));
        fw.append("\n");

        helper++;
      }
    } catch (Exception e) {
      throw new FileNotFoundException(e.toString());
    }

    return System.nanoTime() - start;
  }




  /** Самый обычный main.
   *
   * @param args самый обычный args
   * @throws IOException если FileWriter не создался
   * @throws ExecutionException если не получается получить значение из future
   * @throws InterruptedException если было прервано получение значения из future
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {

    int numbOfLines = 1000;
    int numbOfThreads = 10;

    long timeS = singleThread(new FileWriter("SingleThread.txt", false), numbOfLines);
    long timeM = multiThread(new FileWriter("MultiThread.txt", false), numbOfLines, numbOfThreads);

    StringBuilder str = new StringBuilder();
    str.append("При количестве строк = ")
        .append(numbOfLines).append("\n")
        .append("Количестве потоков = ")
        .append(numbOfThreads).append("\n")
        .append("Однопоточное время = ")
        .append(timeS).append(" наносек\n")
        .append("Многопоточное время = ")
        .append(timeM).append(" наносек\n")
        .append("\nВ данном случае лучше - ");

    if (timeS > timeM) {
      str.append("Многопоточный режим;\n");
    } else {
      str.append("Однопоточный режим;\n");
    }

    logger.log(Level.INFO, str);
  }
}
