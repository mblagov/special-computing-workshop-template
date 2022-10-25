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
   * @param filePath - путь к фалу/директории.
   * @param resFile - текстовый файл, в который выведется "дерево".
   */
  static boolean getFileTree(FileWriter resFile, String filePath) throws FileNotFoundException {
    File firstFile = new File(filePath);
    File[] listOfFiles = firstFile.listFiles();
    if (listOfFiles == null) {
      throw new FileNotFoundException();
    }
    try {
      for (File f : listOfFiles) {
        if (f.isDirectory() && !f.isHidden()) {
          resFile.write(f.getPath() + "\n");
          getFileTree(resFile, f.getAbsolutePath());
        } else if (f.isFile()) {
          resFile.write((f.getName()) + "\n");
        }
      }
      return true;
    } catch (IOException exception) {
      throw new FileNotFoundException();
    }
  }

  public static void main(String[] args) throws IOException  {
    FileWriter resFile = new FileWriter("RESULT.txt", false);
    getFileTree(resFile, "..//");
  }
}
