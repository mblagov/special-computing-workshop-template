package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 3.
 */
class Task3Test {
  
  @Test
  void fileFinderTest1() {
    
    String dir1 = "IAmNotExist";
    File file1 = new File("answer.txt");
    
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.fileFinder(dir1, file1, 0));
    
    assertEquals("java.io.FileNotFoundException: Директория не существует", thrown.toString());
  
    String dir2  = "..//";
    //Здесь file вляется директорией
    File file2 = new File("ans.txt");
  
    thrown = assertThrows(FileNotFoundException.class, () -> Task3.fileFinder(dir2, file2, 0));
  
    assertEquals("java.io.FileNotFoundException: Записывающий файл является директорией!", thrown.toString());
  
    String dir3  = "..//";
    File file3 = new File("..//");
  
    thrown = assertThrows(FileNotFoundException.class, () -> Task3.fileFinder(dir3, file3, 0));
  
    assertEquals("java.io.FileNotFoundException: Записывающий файл является директорией!", thrown.toString());
  }
  
  @Test
  void fileFinderTest2() {
    
    String dir = "..//";
    File file = new File("answer.txt");
    boolean isWorking = true;
    
    try {
      Task3.fileFinder(dir, file, 0);
    } catch (IOException thrown) {
      isWorking = false;
    }
    
    assertTrue(isWorking);
  }
  
  
}