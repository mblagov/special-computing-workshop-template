package ru.spbu.apcyb.svp.tasks.task4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedTan implements Computable {

  private int threads = 10;
  private final TanTask[] buffer = new TanTask[threads];
  private final String numsPath;
  private final String outPath;
  private final long total;

  public MultiThreadedTan(String numsPath, String outPath, long total) {
    this.numsPath = numsPath;
    this.outPath = outPath;
    this.total = total;
  }

  private void readToBuffer(Scanner scanner) {
    int idx = 0;
    while (idx != threads && scanner.hasNext()) {
      String[] pairIntDouble = scanner.next().split(" ");
      int index = Integer.parseInt(pairIntDouble[0]);
      if (index > total) {
        break;
      }
      double num = Double.parseDouble(pairIntDouble[1]);
      buffer[idx] = new TanTask(index, total, num);
      idx++;
    }
    if (idx != threads) {
      threads = idx;
    }
  }

  private void writeToFile(FileWriter fileWriter, Future<String>[] outBuffer)
      throws IOException {

    for (int i = 0; i < threads; i++) {
      try {
        fileWriter.write(outBuffer[i].get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  public void compute() throws IOException {
    File numsFile = new File(numsPath);
    InputStream inputStream = new FileInputStream(numsFile);
    try (Scanner inputScanner = new Scanner(inputStream)) {
      inputScanner.useDelimiter("\n");

      File outFile = new File(outPath);

      try (FileWriter fileWriter = new FileWriter(outFile)) {
        Future<String>[] outBuffer = new Future[threads];

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        readToBuffer(inputScanner);
        while (threads != 0) {

          for (int i = 0; i < threads; i++) {
            Future<String> out = executorService.submit(buffer[i]);
            outBuffer[i] = out;
          }

          writeToFile(fileWriter, outBuffer);
          readToBuffer(inputScanner);
        }

        executorService.shutdown();
      }
    }
  }

}
