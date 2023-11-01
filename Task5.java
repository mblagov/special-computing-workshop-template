package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Задание 5. */
public class Task5 {

  public static Map<String, Long> readFile(String fileName) throws FileNotFoundException {
    try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
      return stream
          .flatMap(l -> Stream.of(l.split("[\\p{Punct}«»–…—„“\\s\\w{1}]")))
          .filter(sym -> !sym.equals(""))
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    } catch (IOException e) {
      throw new FileNotFoundException("Файл не найден");
    }
  }

  public static void writeCounts(Map<String, Long> stream, File file) throws IOException {

    FileOutputStream out;
    if (file.createNewFile()) {
      out = new FileOutputStream(file, false);
    } else {
      out = new FileOutputStream(file);
    }
    try {
      try {
        StringBuilder sb = new StringBuilder();
        stream.forEach(
            (word, count) -> sb.append(word).append(": ").append(count.toString()).append("\n"));
        out.write(sb.toString().getBytes());
      } catch (Exception e) {
        throw new FileSystemException("Невозможно создать файл counts.txt");
      }
    } finally {
      out.close();
    }
  }

  public static void writeWords(Map<String, Long> stream) {
    ThreadPool threadPool = new ThreadPool(10);
    try {
      stream.forEach(
          (word, count) ->
              CompletableFuture.supplyAsync(
                  () -> {
                    try (FileOutputStream out =
                        new FileOutputStream(
                            new File("C:\\Users\\dacen\\Downloads\\МногоСлов\\" + word + ".txt"))) {

                      StringBuilder sb = new StringBuilder();
                      for (int i = 0; i < count; i++) {
                        sb.append(word).append(" ");
                      }
                      out.write(sb.toString().getBytes());

                      return true;
                    } catch (Exception e) {
                      throw new IllegalArgumentException("Невозможно создать файл word.txt");
                    }
                  },
                  threadPool));
    } finally {

      threadPool.stop();
    }
  }

  public static void run(String s1, String s2) throws IOException {
    File file = new File(s1);

    try {
      Map<String, Long> stream = readFile(s2);
      writeCounts(stream, file);
      writeWords(stream);
    } catch (Exception e) {
      throw new IOException("Что-то пошло не так");
    }
  }

  public static void main(String[] args) throws Exception {
    String s1 = "counts.txt";
    String s2 = "ВиМ.txt";
    run(s1, s2);
  }
}
