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
  private final TanTask[] buffer = new TanTask[this.threads];
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
    while (idx != 10 && scanner.hasNext()) {
      String[] pairIntDouble = scanner.next().split(" ");
      int index = Integer.parseInt(pairIntDouble[0]);
      if (index > total) {
        break;
      }
      double num = Double.parseDouble(pairIntDouble[1]);
      this.buffer[idx] = new TanTask(index, this.total, num);
      idx++;
    }
    if (idx != 10) {
      this.threads = idx;
    }
  }

  private void writeToFile(FileWriter fileWriter, Future<String>[] outBuffer)
      throws IOException, ExecutionException, InterruptedException {

    for (int i = 0; i < this.threads; i++) {
      fileWriter.write(outBuffer[i].get());
    }
  }

  @Override
  public void compute() throws IOException, ExecutionException {
    File numsFile = new File(numsPath);
    InputStream inputStream = new FileInputStream(numsFile);
    try (Scanner inputScanner = new Scanner(inputStream)) {
      inputScanner.useDelimiter("\n");

      File outFile = new File(outPath);
      FileWriter fileWriter = new FileWriter(outFile);

      Future<String>[] outBuffer = new Future[this.threads];

      ExecutorService executorService = Executors.newFixedThreadPool(this.threads);
      readToBuffer(inputScanner);
      while (this.threads != 0) {

        for (int i = 0; i < this.threads; i++) {
          Future<String> out = executorService.submit(buffer[i]);
          outBuffer[i] = out;
        }

        try {
          writeToFile(fileWriter, outBuffer);
        } catch (InterruptedException e) {
          e.printStackTrace();
          for (Future<String> stringFuture : outBuffer) {
            stringFuture.cancel(true);
          }
        }
        readToBuffer(inputScanner);
      }

      fileWriter.close();
      executorService.shutdown();
    }
  }

}
