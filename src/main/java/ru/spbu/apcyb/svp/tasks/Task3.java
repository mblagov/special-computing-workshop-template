package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задание 3.
 */
public class Task3 {
  
  /**
   * Вывод списков файлов и папок в заданном директории.
   *
   * @param dir (directory) директория
   * @param file файл для вывода
   * @param s вспомогательная переменная для красивого вывода с пробелами
   * @throws FileNotFoundException если директория не существует или файл является директорией
   */
  static void fileFinder(String dir, File file, int s) throws IOException {
    
    //Проверка на директорию
    if (file.isDirectory()) {
      throw new FileNotFoundException("Записывающий файл является директорией!");
    }
  
    try (FileWriter fr = new FileWriter(file, false)) {
    
      File root = new File(dir);
      File[] list = root.listFiles();
    
      //Проверка на существование
      if (list == null) {
        throw new FileNotFoundException("Директория не существует");
      }
    
      for (File f : list) {
        
        if (f.isDirectory()) {
          
          for (int i = 0; i < s; i++) {
            fr.append(' ');
          }
          
          fr.write("Директория: " + f.getPath());
          fr.append('\n');
  
          fileFinder(f.getAbsolutePath(), file, s + 1);
          
        } else {
          
          for (int i = 0; i < s; i++) {
            fr.append(' ');
          }
          
          fr.write("Файл: " + f.getAbsoluteFile());
          fr.append('\n');
        }
      }
    }

  }
  
  /**
   * Самый обычный мейн.
   *
   * @param args самый обычный args
   * @throws IOException самый обычный exception
   */
  public static void main(String[] args) throws IOException {
    
    File file = new File("answer.txt");
  
    fileFinder("..//", file, 0);
  }
}
