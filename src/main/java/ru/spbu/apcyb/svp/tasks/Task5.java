package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

  public static void streamProccessWords(String textFile, String wordsCountFile) throws IOException {
    try(Stream<String> stream = Files.lines(Paths.get(textFile));
        FileWriter outFile = new FileWriter(wordsCountFile, false)) {

        stream
            .flatMap(line -> Arrays.stream(line.split(" ")))
            .filter(word -> word.length() > 0)
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .forEach(pair -> {
              CompletableFuture.runAsync(() -> {
                try {
                  writeWords2FIle(pair.getKey(), pair.getValue());
                } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
                }
              });
              try {
                outFile.write(pair + "\n");
              } catch (IOException e) {
                throw new RuntimeException("can't write word \"" + pair.getKey() + "\" and their count to file");
              }
            });

    } catch (IOException e) {
      throw new FileNotFoundException("some issues with \"" + wordsCountFile + "\" or \"" + textFile);
    }
  }

  public static void writeWords2FIle(String word, Long wordNumber) throws FileNotFoundException {
    try (FileWriter outFile = new FileWriter(word, false)) {
      for (int i = 0; i < wordNumber; i++) {
        outFile.write(word + "\n");
      }
    } catch (IOException e) {
      throw new FileNotFoundException("\"" + word + "\" didn't opened");
    }
  }

  public static void main(String[] args) throws IOException {
    streamProccessWords("text", "counts");
  }

}