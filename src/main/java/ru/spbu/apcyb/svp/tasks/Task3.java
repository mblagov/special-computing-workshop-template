package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.logging.Logger;

/**
 * Задание 3.
 */
public class Task3 {

  static void walk(String path, File file, int c) throws IOException {

    if (file.isDirectory()) {
      throw new NotDirectoryException("Записывающий файл является директорией!");
    }

    try (FileWriter writer = new FileWriter(file, false)) {

      File root = new File(path);
      File[] list = root.listFiles();

      if (list == null) {
        throw new FileNotFoundException("Директории не существует");
      }

      for (File f : list) {
        if (f.isDirectory()) {
          for (int i = 0; i < c; i++) {
            writer.append(' ');
          }
          writer.write("Directory: " + f.getPath());
          writer.append('\n');
          walk(f.getAbsolutePath(), file, c + 1);
        } else {
          for (int i = 0; i < c; i++) {
            writer.append(' ');
          }
          writer.write("File: " + f.getAbsoluteFile());
          writer.append('\n');
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    File file = new File("answer1.txt");
    walk("..//", file, 0);
  }
}