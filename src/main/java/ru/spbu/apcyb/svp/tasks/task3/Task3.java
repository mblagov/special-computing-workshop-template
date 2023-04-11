package ru.spbu.apcyb.svp.tasks.task3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Задание 3.
 */
public class Task3 {

  public static void main(String[] args) throws IOException {
    var sc = new Scanner(System.in);
    String pathString = sc.next();
    String filename = sc.next();
    getFileTree(pathString, filename);
  }



  public static void getFileTree(String pathString, String filename) throws IOException {
    var cataloguePath = Paths.get(pathString);
    try (var fileWriter = new FileWriter(filename)) {
      for (var element : checkCatalogue(cataloguePath)) {
        fileWriter.write(element + "\n");
      }
      fileWriter.flush();
    }
  }

  /**
   * Метод записывает в список все директории и файлы, находящиеся в данном каталоге.
   *
   * @param cataloguePath
   *     путь к исходному каталогу
   * @return
   *     возвращает список из путей
   * @throws IOException
   *
   */
  public static List<String> checkCatalogue(Path cataloguePath) throws IOException {
    var answer = new ArrayList<String>();
    Files.walkFileTree(
        cataloguePath,
        new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            answer.add(dir.toString());
            return FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            answer.add(file.toString());
            return FileVisitResult.CONTINUE;
          }
        }
    );
    answer.remove(0);
    return answer;
  }
}
