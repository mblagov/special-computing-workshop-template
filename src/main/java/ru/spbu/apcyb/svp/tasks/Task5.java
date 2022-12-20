package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Задание 5.
 */

public class Task5 {

  /**
   * Метод, возвращающий map из слов и их количеств из файла.
   */

  public static Map<String, Long> readFile(String fileName) throws FileNotFoundException {
    try {
      return Files.lines(Paths.get(fileName))
          .flatMap(l -> Stream.of(l.split("[\\p{Punct}«»–…—„“ }\\s\\d\\w{1}]")))
          .filter(item -> !item.equals(""))
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    } catch (IOException e) {
      throw new FileNotFoundException("Файл не найден.");
    }
  }

  /**
   * Метод, создающий файл, в котором выводятся слова и их количество, встретившихся в тексте.
   */
  public static void countFile(Map<String, Long> stream, File file) throws IOException {
    try (FileOutputStream out = new FileOutputStream(file, false)) {
      StringBuilder data = new StringBuilder();
      stream.forEach((word, count) -> data.append(word)
          .append(" - ")
          .append(count.toString())
          .append("\n"));
      out.write(data.toString().getBytes());
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл не найден.");
    }
  }

  /**
   * Метод, создающий для каждого слова отдельный файл с этим словом.
   */
  public static File createFile(String word, Long count) throws FileNotFoundException {
    File file = new File(word + ".txt");
    try (FileOutputStream out = new FileOutputStream(file, false)) {
      StringBuilder data = new StringBuilder();
      for (int i = 0; i < count; i++) {
        data.append(word)
            .append("\n");
      }
      out.write(data.toString().getBytes());
      return file;
    } catch (IOException ex) {
      throw new FileNotFoundException("Файл не найден.");
    }
  }

  /**
   * Многопоточное создание файлов.
   */
  public static void nameFile(Map<String, Long> stream) {
    ThreadPool threadPool = new ThreadPool(10);
    stream.forEach((word, count) -> CompletableFuture.supplyAsync(
        () -> {
          try {
            return createFile(word, count);
          } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
          }
        },
        threadPool
    ));

    threadPool.shutdown();
  }

  /**
   * Main.
   */
  public static void main() throws IOException {
    File file = new File("counts.txt");
    try {
      Map<String, Long> stream = readFile("input.txt");
      countFile(stream, file);
      nameFile(stream);
    } catch (IOException e) {
      throw new IOException("Something went wrong.");
    }
  }
}
