package ru.spbu.apcyb.svp.tasks.task4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedTan implements Runnable{

  private int THREADS = 10;
  private final TanTask[] buffer = new TanTask[this.THREADS];
  private String numsPath;
  private String outPath;
  private long total;

  MultiThreadedTan(String numsPath, String outPath, long total) {
    this.numsPath = numsPath;
    this.outPath = outPath;
    this.total = total;
  }

  private Scanner readToBuffer(Scanner scanner) {
    int cnt = 0;
    while (cnt != 10 && scanner.hasNext()) {
      String[] pairIntDouble = scanner.next().split(" ");
      int index = Integer.parseInt(pairIntDouble[0]);
      if (index > total) {
        break;
      }
      double num = Double.parseDouble(pairIntDouble[1]);
      this.buffer[cnt] = new TanTask(index, this.total, num);
      cnt++;
    }
    if (cnt != 10) {
      this.THREADS = cnt;
    }
    return scanner;
  }

  protected FileWriter writeToFile(FileWriter fileWriter, Future<String>[] outBuffer)
      throws IOException, ExecutionException, InterruptedException {

    for (int i = 0; i < this.THREADS; i++) {
      fileWriter.write(outBuffer[i].get());
    }
    return fileWriter;
  }

  @Override
  public void run() {
    File numsFile = new File(numsPath);
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(numsFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Scanner inputScanner = new Scanner(inputStream);
    inputScanner.useDelimiter("\n");

    File outFile = new File(outPath);
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(outFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Future<String>[] outBuffer = new Future[this.THREADS];

    inputScanner = readToBuffer(inputScanner);
    ExecutorService executorService = Executors.newFixedThreadPool(this.THREADS);

    while (this.THREADS != 0) {

      for (int i = 0; i < this.THREADS; i++) {
        Future<String> value = executorService.submit(buffer[i]);
        outBuffer[i] = value;
      }

      try {
        fileWriter = writeToFile(fileWriter, outBuffer);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      inputScanner = readToBuffer(inputScanner);
    }

    inputScanner.close();
    try {
      fileWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    executorService.shutdown();

  }
}
