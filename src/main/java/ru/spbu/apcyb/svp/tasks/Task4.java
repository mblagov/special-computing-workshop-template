package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.Random;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Задание 4.
 */
public class Task4 {
  private static final Logger logger = LogManager.getLogger(Task4.class);

  public static boolean randomNumbers(Integer count) throws IOException {
    Random rand;
    try {
      rand = SecureRandom.getInstanceStrong();
    } catch (Exception e) {
      throw new IOException("Не удалось сделать последовательность случайных чисел");
    }
    try (FileWriter wr = new FileWriter("numbers.txt", false)) {
      for (int i = 0; i < count; i++) {
        double random = rand.nextDouble()*1000000 - 50000;
        wr.write(random + "\n");
      }
      return true;
    } catch (IOException e) {
      throw new IOException("Ошибка с записью чисел в файл");
    }
  }
  public static Double tanLine(int numbOfLine) throws FileNotFoundException {

    try (BufferedReader br = new BufferedReader(new FileReader("numbers.txt"))) {
      String str = "";
      for (int i = 0; i < numbOfLine; i++) {
        str = br.readLine();
      }
      return Math.tan(Double.parseDouble(str));
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл с числами не найден.");
    }
  }

  public static long MultiThreadTan(FileWriter fw, int countLines, int countThreads)
      throws ExecutionException, InterruptedException, IOException {

    final long start = System.nanoTime();
    TanThread tanThread = new TanThread(countThreads);
    List<Future<Double>> futures = new ArrayList<>();

    for (int i = 0; i < countLines / countThreads; i++) {
      for (int j = 1; j <= countThreads; j++) {

        final int k = j + i * countThreads;
        futures.add(
            CompletableFuture.supplyAsync(
                () -> {
                  try {
                    return tanLine(k);
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
  
  public static long SingleThreadTan(FileWriter fw, int countLines) throws FileNotFoundException {
    long start = System.nanoTime();
    try (BufferedReader br = new BufferedReader(new FileReader("numbers.txt"))) {
      String line;
      long lines = 0;
      while ((line = br.readLine()) != null && lines < countLines) {
        fw.write(String.valueOf(Math.tan(Double.parseDouble(line))));
        fw.append("\n");
        lines++;
      }
    } catch (Exception e) {
      throw new FileNotFoundException(e.toString());
    }
    return System.nanoTime() - start;
  }

  public static boolean Compare(int countLines, int countThreads ) throws IOException, ExecutionException, InterruptedException
  {
    long SingleTime = SingleThreadTan(new FileWriter("SingleThread.txt", false), countLines);
    long MultiTime = MultiThreadTan(new FileWriter("MultiThread.txt", false), countLines, countThreads);

    StringBuilder str = new StringBuilder();
    str.append("Количество строк = ")
        .append(countLines).append("\n")
        .append("Количество потоков = ")
        .append(countThreads).append("\n")
        .append("Однопоточное время = ")
        .append(SingleTime).append("\n")
        .append("Многопоточное время = ")
        .append(MultiTime).append("\n")
        .append("\nВ данном случае эффективнее - ");
    if (SingleTime > MultiTime) {
      str.append("Многопоточный режим;\n");
    } else {
      str.append("Однопоточный режим;\n");
    }
    logger.log(Level.INFO, str);
    return true;
  }
  
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {

    int countLines = 1000;
    int countThreads = 10;

    boolean numb = randomNumbers(countLines);
    boolean work = Compare(countLines, countThreads);
  }
}
