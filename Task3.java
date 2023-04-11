package ru.spbu.apcyb.svp.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задание 3.
 */
public class Task3 {

  static void findFiles(String dir, FileWriter fw, int i) throws IOException {

    File[] list = new File(dir).listFiles();
    if (list == null) {
      throw new FileNotFoundException("Директория не существует");
    }
    for (File f: list) {
      if (f.isDirectory()) {
        fw.append(" ");
        for (int k = 0; k < i; k++) {
          fw.append(".");
        }
        fw.write("Директория: " + f.getPath());
        fw.append("\n");
        findFiles(f.getAbsolutePath(), fw, i + 2);
      } else {
        fw.append(" ");
        for (int k = 0; k < i; k++) {
          fw.append(".");
        }
        fw.write("Файл: " + f.getAbsoluteFile());
        fw.append("\n");
      }
    }
  }

  public static void main(String[] args) throws IOException {

    File file = new File(args[1]);
    if (new File(args[1]).isDirectory()) {
      throw new FileNotFoundException("Целевой файл является директорией");
    }

    try (FileWriter fw = new FileWriter(file, false)) {
      findFiles(args[0], fw, 0);
    } catch (IOException e) {
      throw new IOException("Ошибка при создании FileWriter");
    }
  }
}