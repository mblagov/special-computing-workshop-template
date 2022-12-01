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
  
  /**
   * Вывод списков файлов и папок в заданном директории.
   *
   * @param dir (directory) директория
   * @param fw файл для вывода
   * @param s вспомогательная переменная для красивого вывода с пробелами
   * @throws FileNotFoundException если директория не существует
   */
  static void walk(String dir, FileWriter fw, int s) throws FileNotFoundException {
    
    Logger logger = Logger.getLogger(Task3.class.getName());
    
    File root = new File(dir);
    File[] list = root.listFiles();
    
    if (list == null) {
      throw new FileNotFoundException();
    }
    
    try {
      for (File f : list) {
        
        if (f.isDirectory()) {
          for (int i = 0; i < s; i++) {
            fw.append(' ');
          }
          
          fw.write("Directory: " + f.getPath());
          fw.append('\n');
          
          walk(f.getAbsolutePath(), fw, s + 1);
        } else {
          
          for (int i = 0; i < s; i++) {
            fw.append(' ');
          }
          
          fw.write("File: " + f.getAbsoluteFile());
          fw.append('\n');
        }
      }
    } catch (IOException ex) {
      logger.info(ex.getMessage());
    }
  }
  
  public static void main(String[] args) {
    //useless :(
  }
}
