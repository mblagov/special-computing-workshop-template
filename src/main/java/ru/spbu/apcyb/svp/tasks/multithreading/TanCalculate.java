package ru.spbu.apcyb.svp.tasks.multithreading;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TanCalculate {

  private final int numberOfThreads;

  public TanCalculate(int numberOfThreads) {
    this.numberOfThreads = numberOfThreads;
  }

  public List<Double> calculateTan(List<Double> dataList)
      throws IOException, InterruptedException {
    if (numberOfThreads < 10) {
      ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfThreads);
      try {

        Future<List<Double>> futuresList = executorService.submit(
            () -> dataList.parallelStream().map(Math::tan).toList());
        executorService.shutdown();
        return futuresList.get();

      } catch (InterruptedException | ExecutionException ex) {
        Thread.currentThread().interrupt();
        throw new InterruptedException("Error in thread");
      }
    } else {
      throw new IOException("The program simultaneously processes no more than 10 values.");
    }
  }
}

