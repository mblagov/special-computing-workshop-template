package ru.spbu.apcyb.svp.tasks;

/**
 * Задание 5.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task5 {

  private Map<String, Long> map;

  public void readAndCount(Path path) throws IOException {
    this.map = Arrays.stream(Files.readAllLines(path).toArray(new String[]{}))
        .flatMap(line -> Arrays.stream(line.split("\\p{Punct}|[0-9]+|\t|\\s+"))
            .map(word -> Normalizer
                .normalize(word, Normalizer.Form.NFD)
                .replaceAll("[^\\u0401\\u0451\\u0410-\\u044f|\\p{ASCII}]", ""))
            .filter(item -> !item.isEmpty())
            .map(String::toLowerCase))
        .collect(Collectors.groupingBy(
            Function.identity(), Collectors.counting()));
  }

  public String mapToString() {
    return map.entrySet().stream()
        .map(e -> e.getKey() + " = " + e.getValue())
        .collect(Collectors.joining("\n"));
  }

  public void printCount(Path path) throws IOException {
    Files.write(path, Collections.singleton(mapToString()), StandardOpenOption.APPEND);
  }

  public void parallelFileCount(Path dirPath) throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    ArrayList<CompletableFuture> list = new ArrayList<>();
    for (var entry : this.map.entrySet()) {
      long numOfCopies = entry.getValue();
      Path newPath = dirPath.resolve(entry.getKey() + ".txt");
      String word = entry.getKey();
      for (int i = 0; i < numOfCopies; i++) {
        list.add(CompletableFuture.runAsync(() -> {
          try {
            Files.write(newPath, Collections.singleton(word), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }, executor));
      }
    }
    CompletableFuture<String>[] a = new CompletableFuture[0];
    Stream.of(list.toArray(a)).map(CompletableFuture::join).collect(Collectors.joining("\n"));
    executor.shutdown();
  }
}