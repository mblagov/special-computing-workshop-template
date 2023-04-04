package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;


/**
 * Задание 3.
 */


public class Task3 {

  /**
   * Метод для вывода в файл файловго дерева.
   *
   * @param urlFile - путь к фалу/директории.
   * @param file - текстовый файл, в который выведется "дерево".
   */
  static boolean searchFiles(File file, String urlFile) throws IOException {
    if (file.isDirectory()) {
      throw new NotDirectoryException("Записывающий файл является директорией");
    }
    try (FileWriter writerFile = new FileWriter(file, true)) {
      File firstFile = new File(urlFile);
      File[] listOfFiles = firstFile.listFiles();
      if (listOfFiles == null) {
        throw new FileNotFoundException("Файл не существует");
      }
      try {
        for (File f : listOfFiles) {
          if (f.isDirectory() && !f.isHidden()) {
            writerFile.write(f.getPath() + "\n");
            searchFiles(file, f.getAbsolutePath());
          } else if (f.isFile()) {
            writerFile.write((f.getName()) + "\n");
          }
        }
        return true;
      } catch (IOException exception) {
        throw new FileNotFoundException("Файл не существует");
      }
    }
  }

  public static void main(String[] args) throws IOException {
    File writerFile = new File("result.txt");
    searchFiles(writerFile, "..//");
  }
}