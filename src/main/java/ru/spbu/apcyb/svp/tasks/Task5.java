package ru.spbu.apcyb.svp.tasks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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

  public static void wordsSort(String wordsFile, String counts) throws FileNotFoundException, RuntimeException, IOException {
    ThreadPool threadPool = new ThreadPool(10);
    try (Stream<String> stream = Files.lines(Paths.get(wordsFile));
        BufferedWriter toCountsFile = new BufferedWriter
            (new OutputStreamWriter(new FileOutputStream((counts))))) {
      stream.flatMap(line -> Arrays.stream(line.trim().split(" ")))
          .map(word -> word.replaceAll("\\P{L}", " ").trim())
          .map(String::toLowerCase)
          .filter(word -> word.length() > 0)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet()
          .forEach(word -> {
            try {
              toCountsFile.write(word + "\n");
            } catch (IOException e) {
              throw new RuntimeException("Нельзя записать в файл counts.txt");
            }
            String[] strWord = String.valueOf(word).split("=");
            String thisWord = strWord[0];
            Integer thisWordCount = Integer.parseInt(strWord[1]);
            if (Files.exists(Path.of(thisWord + ".txt"))) {
              CompletableFuture.runAsync(() -> {
                try {
                  writeWordsToFile(thisWord,thisWordCount);
                } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
                }

              });
            } else {
              CompletableFuture.runAsync(() -> {
                    try {
                      writeWordsToNewFile(thisWord,thisWordCount);
                    } catch (FileNotFoundException e) {
                      throw new RuntimeException(e);
                    }

                  },
                  threadPool
              );
            }
          });
    } catch (IOException e) {
      throw new FileNotFoundException("Ошибка какого-то файла");
    }
    finally
    {
      threadPool.shutdown();
    }
  }

  public static void writeWordsToFile(String word, Integer wordCount) throws FileNotFoundException {
    try (BufferedWriter wordFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream((word + ".txt"))))) {
      for (int i = 0; i < wordCount; i++) {
        wordFile.write(word);
        wordFile.write("\n");
      }
    } catch (IOException e) {
      throw new FileNotFoundException("Нельзя открыть файл для слова " + word);
    }
  }

  public static void writeWordsToNewFile(String word, Integer wordCount) throws FileNotFoundException {
    try (FileWriter wordFile = new FileWriter(word + ".txt", true)) {
      writeWordsToFile(word, wordCount);
    } catch (IOException ex) {
      throw new FileNotFoundException("Нельзя открыть файл для нового слова " + word);
    }
  }

  public static void main(String[] args) throws IOException {
    wordsSort("task5.txt", "counts.txt");
  }

}