package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Задание 3.
 */
public class Task3 {

  protected void printFiles(File curRoot, FileWriter fileWriter) throws IOException {
    for (File curFile : curRoot.listFiles()) {
      if (curFile.isDirectory()) {
        printFiles(curFile, fileWriter);
      } else {
        fileWriter.write(curFile.getPath().concat("\n"));
      }
    }
  }

  private void mainLogic(String root, String output) throws IOException {
    File rootDir = new File(root);
    if (!rootDir.exists() || !rootDir.isDirectory()) {
      throw new IOException("error with dir");
    }
    File outputFile = new File(output);
    try (FileWriter fileWriter = new FileWriter(outputFile)) {
      this.printFiles(rootDir, fileWriter);
    } catch (IOException e) {
      throw new IOException("cant write into file");
    }
  }

  /**
   * Программа выводит содержимое дериктории в отдельный файл.
   *
   * @param args - путь до директории, содержимое которой необходимо вывести и путь до файла, в
   *             который осуществляется вывод.
   * @throws IOException ошибка в задании пути до дериктории.
   */

  public static void main(String[] args) throws IOException {
    Task3 task3 = new Task3();
    task3.mainLogic(args[0], args[1]);

  }
}