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

  static void fFinder(String direct, File file, int s) throws IOException {

    try (FileWriter filewr = new FileWriter(file, false)) {
      File root = new File(direct);
      File[] list = root.listFiles();
      if (list == null) {
        throw new FileNotFoundException("Директория не существует");
      }
      for (File f : list) {
        if (f.isDirectory()) {
          for (int i = 0; i < s; i++) {
            filewr.append(' ');
          }
          filewr.write("Директория: " + f.getPath());
          filewr.append('\n');
          fFinder(f.getAbsolutePath(), file, s + 1);
        }
        else {
          for (int i = 0; i < s; i++) {
            filewr.append(' ');
          }
          filewr.write("Файл: " + f.getAbsoluteFile());
          filewr.append('\n');
        }
      }
    }
    if (file.isDirectory()) {
      throw new FileNotFoundException("Записывающий файл - директория.");
    }
  }
  public static void main(String[] args) throws IOException {
    File file = new File("answ.txt");
    fFinder("..//", file, 0);
  }
}
