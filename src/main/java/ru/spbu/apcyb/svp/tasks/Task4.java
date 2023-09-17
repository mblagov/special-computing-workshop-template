package ru.spbu.apcyb.svp.tasks;

/**
 * Задание 4.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Task4 {

  final ArrayList<Double> array = new ArrayList<>();

  public Task4(String input) throws FileNotFoundException {
    Scanner numFile = new Scanner(new File(input));
    Scanner sc = new Scanner(numFile.nextLine());
    sc.useDelimiter(" ");
    while (sc.hasNextDouble()) {
      this.array.add(sc.nextDouble());
    }
    sc.close();
    numFile.close();
  }

  public String calculate(int upperBound) throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    StringBuilder buffer = new StringBuilder();
    buffer.append(Math.min(upperBound, array.size())).append(" numbers were processed\n");
    List<CompletableFuture<String>> list = new ArrayList<>();
    for (int i = 0; (i < array.size()) && (i < upperBound); i++) {
      Double element = array.get(i);
      list.add(CompletableFuture
          .supplyAsync(() -> "val = " + element + "; tan(val) = " + Math.tan(element), executor));
    }
    CompletableFuture<String>[] a = new CompletableFuture[0];
    String result = Stream.of(list.toArray(a)).map(CompletableFuture::join)
        .collect(Collectors.joining("\n"));
    buffer.append(result);
    executor.shutdown();
    return buffer.toString();
  }
}