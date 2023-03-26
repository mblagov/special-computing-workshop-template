package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задание 3.
 */


public class Task3 {

  /**
   * Метод для вывода в файл файловго дерева.
   *
   * @param urlFile - путь к фалу/директории.
   * @param writerFile - текстовый файл, в который выведется "дерево".
   */
  static boolean searchFiles(FileWriter writerFile, String urlFile) throws FileNotFoundException {
    File firstFile = new File(urlFile);
    File[] listOfFiles = firstFile.listFiles();
    if (listOfFiles == null) {
      throw new FileNotFoundException();
    }
    try {
      for (File file : listOfFiles) {
        if (file.isDirectory() && !file.isHidden()) {
          writerFile.write(file.getPath() + "\n");
          searchFiles(writerFile, file.getAbsolutePath());
        } else if (file.isFile()) {
          writerFile.write((file.getName()) + "\n");
        }
      }
      return true;
    } catch (IOException exception) {
      throw new FileNotFoundException();
    }
  }

  public static void main(String[] args) throws IOException {
    FileWriter writerFile = new FileWriter("result.txt", false);
    searchFiles(writerFile, "..//");
  }
}