package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

  public static final Logger logger = Logger.getLogger(Task5.class.getName());
  public static final String PATH = "./src/main/resources/Task5/";
  public static final String RESOURCES = "./src/main/resources/Task5/resources/";

  /**
   * Выводит в существующий для слова word файл(с названием word.txt) это слово ровно столько раз,
   * сколько оно встретилось в исходном тексте.
   *
   * @param word    слово.
   * @param counter число вхождений.
   */
  public static void writeWords(String word, Integer counter)
      throws FileNotFoundException {
    try (BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(PATH + word + ".txt")))) {
      for (int i = 0; i < counter; i++) {
        writer.write(word + "\n");
      }
    } catch (IOException e) {
      throw new FileNotFoundException(
          "Не получилось открыть файл с названием \"" + word + ".txt\"");
    }
  }

  /**
   * Создает файл для слова word(с названием word.txt).
   *
   * @param word    слово.
   * @param counter число вхождений.
   */
  public static void createFile(String word, Integer counter)
      throws FileNotFoundException {
    try (FileWriter wordFile = new FileWriter(PATH + word + ".txt", true)) {
      writeWords(word, counter);
    } catch (IOException e) {
      throw new FileNotFoundException(
          "Не получилось создать файл \"" + word + ".txt\"");
    }
  }

  /**
   * Считает число вхождений каждого слова в книгу bookFile, выводит данные в файл с результатом
   * counterFile, и в зависимости от того, встречалось уже слово или нет, вызывает методы writeWords
   * и createFile.
   *
   * @param bookFile    название файла с исходным текстом.
   * @param counterFile название файла для вывода данных.
   */
  public static void processBook(String bookFile, String counterFile)
      throws IOException {
    try (Stream<String> stream = Files.lines(Paths.get(bookFile));
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(counterFile)))) {
      stream.flatMap(line -> Arrays.stream(line.trim().split(" ")))
          .map(word -> word.replaceAll("\\P{L}", " ").trim())
          .map(String::toLowerCase)
          .filter(word -> word.length() > 0)
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet()
          .forEach(word -> {
            try {
              writer.write(word + "\n");
            } catch (IOException e) {
              throw new RuntimeException("Ошибка с файлом counts.txt");
            }
            List<String> arr = List.of(word.toString().split("="));
            String currentWord = arr.get(0);
            Integer counter = Integer.parseInt(arr.get(1));
            if (Files.exists(Path.of(currentWord + ".txt"))) {
              CompletableFuture.runAsync(() -> {
                try {
                  writeWords(currentWord, counter);
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
            } else {
              CompletableFuture.runAsync(() -> {
                try {
                  createFile(currentWord, counter);
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
            }
          });
    } catch (IOException e) {
      throw new FileNotFoundException(
          "Ошибка с \"" + bookFile + "\" или \"" + counterFile + "\"!");
    }
  }

  /**
   * Просто мейн.
   */
  public static void main(String[] args) throws IOException {
    processBook(RESOURCES + "book.txt", RESOURCES + "counts.txt");
  }
}
