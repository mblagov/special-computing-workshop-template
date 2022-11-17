package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Задание 3.
 */
public class Task3 {

  public static final Logger logger = Logger.getLogger(Task3.class.getName());

  /**
   * Перебор дерева директорий, содержащихся в current.
   *
   * @param current - текущая директория/файл.
   * @param depth   - текущая "глубина" относительно "коря".
   * @return - дерево директорий в виде строки.
   */
  public static StringBuilder fileTree(File current, String depth) {
    StringBuilder path = new StringBuilder(depth + current.getName() + "\n");
    if (current.isDirectory()) {
      for (File item : current.listFiles()) {
        path.append(fileTree(item, depth + "-"));
      }
    } else {
      return new StringBuilder(depth + current.getName() + "\n");
    }
    return path;
  }

  /**
   * Записывает дерево из дериктории args[0] в файл args[1].
   *
   * @param args - args[0], args[1].
   */
  public static void main(String[] args) {
    File folder = new File(args[0]);
    if (folder.exists()) {
      try (FileWriter writer = new FileWriter(args[1], false)) {
        String fileTree = String.valueOf(fileTree(folder, ""));
        writer.write(fileTree);
        writer.flush();
      } catch (IOException exception) {
        logger.info(exception.getMessage());
      }
    } else {
      logger.info("Директория для обхода не существует(неверный путь)");
    }

  }
}
