package ru.spbu.apcyb.svp.tasks.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Задание 3.
 */
public class Task3 {

  private static void getFileStructure(String dirPath, int level, FileWriter out)
      throws IOException {
    File dir = new File(dirPath);
    if (!dir.exists()) {
      throw new IOException("No such File or Directory: " + dir);
    }
    String[] indentation = new String[level];
    Arrays.fill(indentation, "    ");
    out.write(
        String.join("", Arrays.asList(indentation)) +
            dir.getName() +
            (dir.isDirectory() ? ":" : "") +
            "\n"
    );
    File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        getFileStructure(file.getPath(), level + 1, out);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String dirPath = args[0];
    String outPath = args[1];
    File outCheck = new File(outPath);
    if (!outCheck.exists()) {
      throw new FileNotFoundException("Incorrect Path: " + outPath);
    }
    FileWriter out = new FileWriter(outPath);
    try {
      getFileStructure(dirPath, 0, out);
    } finally {
      out.close();
    }
  }
}
