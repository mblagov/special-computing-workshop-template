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

    FileWriter writer = new FileWriter(file2Save);
    File currentDir = new File(path).getAbsoluteFile();
    displayDirectoryContents(currentDir, writer);
    writer.close();
  }

  public static void displayDirectoryContents(File dir, FileWriter writer)
      throws IOException {
    if (!dir.exists()) {
      throw new FileNotFoundException("directory doesn't exists");
    } else if (!dir.isDirectory()) {
      throw new NotDirectoryException("it's not a directory");
    }

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