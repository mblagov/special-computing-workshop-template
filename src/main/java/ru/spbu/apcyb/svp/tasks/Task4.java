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
