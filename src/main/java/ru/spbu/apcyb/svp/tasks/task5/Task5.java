package ru.spbu.apcyb.svp.tasks.task5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {


  /**
   * главный метод программы.
   *
   * @param args
   *     массив аргументов
   * @throws IOException
   *
   * @throws ExecutionException
   *
   * @throws InterruptedException
   *
   */
  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    var filename = "./src/main/resources/book.txt";
    var outFilename = "./src/main/resources/counts.txt";

    var stream = readFile(filename);
    var map = countWords(stream, outFilename);
    writeWordsToFiles(map, 10, "./src/main/resources/wordFiles/");

  }

  /**
   * метод, переводящий содержимое файла в поток слов.
   *
   * @param filename
   *     считываемый файл
   * @return
   *
   * @throws IOException
   *
   */
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
      stream = stream.filter(x -> !x.isEmpty()
          && (x.matches("[a-zA-Z]+") || x.matches("[а-яА-Я]+")));
      return stream;
    }
  }

  /**
   * метод подсчета слов в потоке.
   *
   * @param stream
   *     Поток слов
   * @param outFileName
   *     Файл, куда записываются все слова и количество их вхождений
   * @return
   *     Возвращает карту слово - количество
   * @throws IOException
   *
   */
  public static Map<String, Integer> countWords(
      Stream<String> stream,
      String outFileName) throws IOException {
    var result = stream.collect(Collectors.groupingBy(x -> x, Collectors.summingInt(x -> 1)));
    try (var fileWriter = new FileWriter(outFileName)) {
      for (var entry : result.entrySet()) {
        fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
      }
      fileWriter.flush();
    }
    return result;
  }

  /**
   * метод многопоточной записи каждого слова в отдельный файл.
   *
   * @param mapOfWords
   *     карта слов и их вхождений
   * @param threadsMaxCount
   *     максимальное количество потоков
   * @param path
   *     каталог, где будут созданы все эти файлы
   * @throws IOException
   *
   * @throws ExecutionException
   *
   * @throws InterruptedException
   *
   */
  public static void writeWordsToFiles(
      Map<String, Integer> mapOfWords,
      int threadsMaxCount,
      String path)
      throws IOException, ExecutionException, InterruptedException {
    Future<String>[] futures = new Future[mapOfWords.size()];
    var executorService = Executors.newFixedThreadPool(threadsMaxCount);
    Map.Entry<String, Integer>[] arrayOfEntries
        =  mapOfWords.entrySet().toArray(new Entry[mapOfWords.size()]);
    for (int i = 0; i < mapOfWords.size(); i++) {
      var entry = arrayOfEntries[i];
      futures[i] = executorService.submit(
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
      String newFileName = path + entry.getKey() + ".txt";
      try (var fileWriter = new FileWriter(newFileName)) {
        fileWriter.write(futures[i].get());
      }
    }
  }
}
