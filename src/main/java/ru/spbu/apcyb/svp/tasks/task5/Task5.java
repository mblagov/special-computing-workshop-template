package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    var filename = "./src/main/resources/book.txt";
    var outFilename = "./src/main/resources/counts.txt";

    var stream = readFile(filename);
    var map = countWords(stream, outFilename);
    writeWordsToFiles(map);

  }

  public static Stream<String> readFile(String filename) throws IOException {
    try (var bufferedReader = new BufferedReader(new FileReader(filename))) {
      var stringBuilder = new StringBuilder();
      var line = bufferedReader.readLine();
      while (line != null) {
        stringBuilder.append(line + " ");
        line = bufferedReader.readLine();
      }
      var finalString = stringBuilder.toString();
      var words = finalString.split("[ .,!()-]");
      var stream = Arrays.stream(words);
      stream = stream.filter(x -> !x.isEmpty() && x.matches("[a-zA-Z]+"));
      stream = stream.filter(x -> x.matches("а-яА-Я]+"));
      return stream;
    }
  }

  public static Map<String, Integer> countWords(Stream<String> stream, String outFileName) throws IOException {
    var result = stream.collect(Collectors.groupingBy(x -> x, Collectors.summingInt(x -> 1)));
    try (var fileWriter = new FileWriter(outFileName)) {
      for (var entry : result.entrySet()) {
        fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
      }
      fileWriter.flush();
    }
    return result;
  }

  public static void writeWordsToFiles(Map<String, Integer> mapOfWords)
      throws IOException, ExecutionException, InterruptedException {
    Future<String>[] futures = new Future[mapOfWords.size()];
    Map.Entry<String, Integer>[] arrayOfEntries =  mapOfWords.entrySet().toArray(new Entry[mapOfWords.size()]);
    for (int i = 0; i < mapOfWords.size(); i++) {
      var entry = arrayOfEntries[i];
      futures[i] = CompletableFuture.supplyAsync(
          () -> {
            var sb = new StringBuilder();
            for (int j = 0; j < entry.getValue(); j++) {
              sb.append(entry.getKey() + " ");
            }
            return sb.toString();
          });
    }
    for (int i = 0; i < mapOfWords.size(); i++) {
      var entry = arrayOfEntries[i];
      String newFileName = "./src/main/resources/wordFiles/" + entry.getKey() + ".txt";
      try (var fileWriter = new FileWriter(newFileName)) {
        fileWriter.write(futures[i].get());
      }
    }
  }
}
