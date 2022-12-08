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
   * @param fr файл для вывода
   * @param s вспомогательная переменная для красивого вывода с пробелами
   * @throws FileNotFoundException если директория не существует или файл является директорией
   */
  static void fileFinder(String dir, FileWriter fr, int s) throws IOException {
  
    File[] list = new File(dir).listFiles();
  
    assert list != null;
    
    for (File f : list) {
  
      if (f.isDirectory()) {
    
        for (int i = 0; i < s; i++) {
          fr.append(' ');
        }
    
        fr.write("Директория: " + f.getPath());
        fr.append('\n');
    
        fileFinder(f.getAbsolutePath(), fr, s + 1);
    
      } else {
    
        for (int i = 0; i < s; i++) {
          fr.append(' ');
        }
    
        fr.write("Файл: " + f.getAbsoluteFile());
        fr.append('\n');
    
      }
    }

  }
  
  /**
   * Проверка директории и файла для вывода.
   *
   * @param dir (directory) директория
   * @param file файл для вывода
   * @return список файлов
   * @throws IOException если директория не существует или файл является директорией
   */
  public static FileWriter check(String dir, File file) throws IOException {
  
    //Проверка на директорию
    if (file.isDirectory()) {
      throw new FileNotFoundException("Записываемый файл является директорией!");
    }
    
    //Проверка на существование
    if (new File(dir).listFiles() == null) {
      throw new FileNotFoundException("Директория не существует");
    }
  
    FileWriter fr;
  
    try {
      fr = new FileWriter(file, false);
    } catch (IOException e) {
      throw new IOException("Ошибка при создании FileWriter!");
    }
    
    return fr;
  }
  
  /**
   * Самый обычный мейн.
   *
   * @param args самый обычный args
   * @throws IOException самый обычный exception
   */
  public static void main(String[] args) throws IOException {
  
    String dir = "..//";
    File file = new File("answer.txt");
  
    fileFinder(dir, check(dir, file), 0);
  }
}
