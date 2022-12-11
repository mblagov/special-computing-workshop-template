package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.Scanner;

/**
 * Задание 3.
 */
public class Task3 {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    String path = scanner.nextLine();
    String file2Save = scanner.nextLine();
    scanner.close();

    try (FileWriter writer = new FileWriter(file2Save)) {
      File currentDir = new File(path).getAbsoluteFile();
      displayDirectoryContents(currentDir, writer);
    }
  }

  public static void checkDirectory(File dir) throws FileNotFoundException, NotDirectoryException {
    if (!dir.exists()) {
      throw new FileNotFoundException("directory doesn't exists");
    } else if (!dir.isDirectory()) {
      throw new NotDirectoryException("it's not a directory");
    }
  }

  public static void displayDirectoryContents(File dir, FileWriter writer)
      throws IOException {
    checkDirectory(dir);

    File[] files = dir.listFiles();
    assert files != null;
    for (File file : files) {
      if (file.isDirectory()) {
        writer.write("directory:" + file.getCanonicalPath() + "\n");
        displayDirectoryContents(file, writer);
      } else {
        writer.write("     file:" + file.getCanonicalPath() + "\n");
      }
    }
  }

}