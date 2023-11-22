package ru.spbu.apcyb.svp.tasks.directoryscanner;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для вывода всех каталогов и файлов.
 */
public class FilePrint {

  /**
   * Конструктор класса.
   */
  private FilePrint() {
  }

  /**
   * Метод, который преобразует полученные строки в путь и вызывает метод, который их выписывает в
   * файл.
   *
   * @param stringPathRead - строка пути к каталогу, который хотим просканирвоать
   * @param stringPathWrite - строка пути к файлу, в который хотим записать информаицию
   * @throws IOException - исключение
   */
  public static void scanToFile(String stringPathRead, String stringPathWrite) throws IOException {

    Path readPath = Path.of(stringPathRead).toAbsolutePath();
    Path writePath = Path.of(stringPathWrite).toAbsolutePath();
    if (!(Files.isDirectory(readPath) && Files.isDirectory(writePath))) {
      throw new IllegalArgumentException("The path is not right");
    }
    scanToFile(readPath, writePath);
  }

  /**
   * Метод, который записывает в файл все каталоги(файлы), которые были обнаружены при
   * сканировании.
   *
   * @param pathRead - путь к каталогу, который нужно просканировать
   * @param pathWrite - путь к файлу, в который записывается информация
   * @throws IOException - исключение
   */
  public static void scanToFile(Path pathRead, Path pathWrite) throws IOException {
    Directory scan = new Directory(pathRead);
    List<Path> result = scan.traversal();
    String savePath = String.valueOf(pathWrite);

    StringBuilder builder = new StringBuilder();
    for (Path path : result) {
      builder.append(path.toString()).append("\n");
    }

    try (FileWriter write = new FileWriter(savePath)) {
      String res = builder.toString();
      write.write(res);

    } catch (IOException ex) {
      throw new IOException("Incorrect data");
    }
  }
}
