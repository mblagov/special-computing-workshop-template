package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Задание 3.
 */
public class Task3 {

  private static void getFileStructure(String dirPath, int level, FileWriter out) {
    File dir = new File(dirPath);
    String[] indentation = new String[level];
    Arrays.fill(indentation, "    ");
    try {
      out.write(
          String.join("", Arrays.asList(indentation)) +
              dir.getName() +
              (dir.isDirectory() ? ":" : "") +
              "\n"
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
    File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        getFileStructure(file.getPath(), level + 1, out);
      }
    }
  }

  public static void main(String[] args) {
    String dirPath = args[0];
    String outPath = args[1];
    try {
      FileWriter out = new FileWriter(outPath);
      getFileStructure(dirPath, 0, out);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
