package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CalculateTan implements Runnable {
  String outFile;
  float value;

  public CalculateTan(float value, String outFile) {
    this.value = value;
    this.outFile = outFile;
  }

  @Override
  public void run() {
    try (FileWriter writer = new FileWriter(outFile, true)){
      writer.write(String.valueOf(Math.tan(this.value)));
      writer.write("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

/**
 * Задание 4.
 */
public class Task4 {
  public static void multiThreadTan(String inFile, String outFile, int numberOfLines, int numberOfThreads)
      throws FileNotFoundException {
    try (BufferedReader readBuffer = new BufferedReader(new FileReader(inFile))) {
      new FileWriter(outFile, false).close(); // just to clear file

      long startTime = System.nanoTime();

      ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
      for (int i = 0; i < numberOfLines; i++) {
        float val = Float.parseFloat(readBuffer.readLine());
        executorService.execute(new CalculateTan(val, outFile));
      }
      executorService.shutdown();

      long endTime = System.nanoTime();
      long timeDelta = endTime - startTime;
      System.out.println("MultiThread:   " + timeDelta);
    } catch (IOException e) {
      throw new FileNotFoundException("incorrect input file");
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    multiThreadTan("numbers", "testfile", 10000, 10);
  }
}