package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Задание 3.
 */
public class Task3 {

  static void walk(String path, FileWriter writer, int c) throws IOException {

    Logger logger = Logger.getLogger(Task3.class.getName());

    File root = new File(path);
    File[] list = root.listFiles();

    if (list == null) {
      throw new FileNotFoundException();
    }

    try {
      for (File f : list) {
        if (f.isDirectory()) {
          for (int i = 0; i < c; i++) {
            writer.append(' ');
          }
          writer.write("Directory: " + f.getPath());
          writer.append('\n');
          walk(f.getAbsolutePath(), writer, c + 1);
        } else {
          for (int i = 0; i < c; i++) {
            writer.append(' ');
          }
          writer.write("File: " + f.getAbsoluteFile());
          writer.append('\n');
        }
      }
    } catch (IOException ex) {
      logger.info(ex.getMessage());
    }
  }

  public static void main(String[] args) throws IOException {
    FileWriter writer = new FileWriter("answer.txt", false);
    walk("..//", writer, 0);
  }
}