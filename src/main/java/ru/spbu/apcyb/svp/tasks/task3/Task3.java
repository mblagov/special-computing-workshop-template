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

/**
 * Задание 3.
 */
public class Task3 {

  /**
   * главный метод программы.
   *
   * @param args
   *     аргументы программы
   * @throws IOException
   *
   */

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Некорректное количество аргументов программы");
    }
    String pathString = args[0];
    String filename = args[1];
    getFileTree(pathString, filename);
  }

  /**
   * метод получения списка каталогов и файлов и записи его в файл.
   *
   * @param pathString
   *     строка, содержащая путь, начиная с которого будет исследоваться дерево каталогов
   * @param filename
   *     строка, содержащая имя файла, в который производится запись
   * @throws IOException
   *
   */

  public static void getFileTree(String pathString, String filename) throws IOException {
    var cataloguePath = Paths.get(pathString);
    try (var fileWriter = new FileWriter(filename, false)) {
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
            if (!cataloguePath.equals(dir)) {
              answer.add(dir.toString());
            }
            return FileVisitResult.CONTINUE;
          }

          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            answer.add(file.toString());
            return FileVisitResult.CONTINUE;
          }
        }
    );
    return answer;
  }
}
