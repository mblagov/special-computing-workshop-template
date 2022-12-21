package ru.spbu.apcyb.svp.tasks.task5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

  public static Map<String, Integer> readInputFile(String path) throws IOException {
    try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
      Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
      stream
          .flatMap(
              line -> Stream.of(
                  line
                  .toLowerCase()
                  .replaceAll("\\p{Punct}", " ")
                  .split("\\s+")
              )
          )
          .forEach(word -> {
            if (!word.isEmpty() && !word.equals(" ")) {
              concurrentHashMap.merge(word, 1, (prev, next) -> prev + 1);
            }
          });
      return concurrentHashMap;
    } catch (IOException e) {
      throw new IOException("Can't read file: " + path);
    }
  }


  public static void fillCountsFile(Map<String, Integer> input, String outDir) throws IOException {
    File outFile = new File(outDir);
    try (PrintWriter printWriter = new PrintWriter(outFile)) {
      input.forEach((key, value) -> printWriter.write(key + ": " + value.toString() + "\n"));
    } catch (IOException e) {
      throw new IOException("Can't write into file");
    }
  }


  public static void countAllWords(String inDir, String listDir, String outDir) throws IOException {
    Map<String, Integer> map = readInputFile(inDir);
    fillCountsFile(map, listDir);
    map.forEach((key, value) -> CompletableFuture.runAsync(new PrintWords(value, key, outDir)));
  }


  public static void main(String[] args) throws IOException {
    countAllWords(
        "src/main/resources/War and Peace.txt",
        "src/main/resources/counts.txt",
        "src/main/resources/OutTask5"
    );
  }

}
