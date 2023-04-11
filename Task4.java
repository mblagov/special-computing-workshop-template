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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {
  public static final String INPUT = "numbers.txt";
  public static final Logger logger = Logger.getLogger(Task4.class.getName());

  public static boolean generateNums(int count, String inputAddress) throws IOException {

    Random rand;
    try {
      rand = SecureRandom.getInstanceStrong();

    } catch (Exception e) {
      throw new IOException("Ошибка генерации последовательности чисел");
    }
    try (FileWriter fw = new FileWriter(inputAddress, false)) {
      for (int i = 0; i < count; i++) {
        double randNum = rand.nextDouble() * 73 - 37;
        fw.write(randNum + "\n");
      }
      return true;
    } catch (IOException e) {
      throw new IOException("Ошибка записи чисел в файл");

    }
  }
  public static long tanSingle(int count, FileWriter fw, String inputAddress) throws FileNotFoundException {
    long start = System.nanoTime();
    try (BufferedReader br = new BufferedReader(new FileReader(inputAddress))) {
      String line;
      long l = 0;
      while ((line = br.readLine()) != null && l < count) {
        fw.write(String.valueOf(Math.tan(Double.parseDouble(line))));
        fw.append("\n");
        l++;
      }
    } catch (Exception e) {
      throw new FileNotFoundException("Ошибка чтения файла в однопоточном режиме");
    }
    return System.nanoTime() - start;
  }

  public static long tanThreaded(int numLines, int numFlows, FileWriter fw, String inputAddress) {
    final long start = System.nanoTime();

    List < Tan > tans = new ArrayList < > ();
    int numsPerThread = numLines / numFlows;
    try {
      for (int i = 0; i < numFlows; i++) {
        Tan tan = new Tan(i * numsPerThread + 1, numsPerThread, fw, inputAddress);
        tans.add(tan);
        tan.start();

      }
    } finally {
      try {
        for (Tan tan: tans) {
          tan.join();

        }
      } catch (InterruptedException e) {
        logger.warning("InterruptedException!" + e);
        Thread.currentThread().interrupt();
      }
    }
    return System.nanoTime() - start;

  }

  public static void main(String[] args) throws IOException {
    int numLines = 100;
    int numFlows = 10;

    generateNums(numLines, INPUT);
    long singleTime;
    try (FileWriter outSingle = new FileWriter("Single.txt", false)) {
      singleTime = tanSingle(numLines, outSingle, INPUT);
    } catch (IOException e) {
      throw new IOException("Ошибка с файлом для вывода в однопоточном режиме");
    }
    long multiTime;
    try (FileWriter outMulti = new FileWriter("Multi.txt", false)) {
      multiTime = tanThreaded(numLines, numFlows, outMulti, INPUT);
    } catch (IOException e) {
      throw new IOException("Ошибка с файлом для вывода в многопоточном режиме");
    }
    String str = "Количество строк = " +
        numLines + "\n" +
        "Количество потоков = " +
        numFlows + "\n" +
        "Однопоточное время = " +
        singleTime + " нс\n" +
        "Многопоточное время = " +
        multiTime + " нс\n";
    logger.log(Level.INFO, str);

  }
}