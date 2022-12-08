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
    
    String[] args = {"IAmNotExist", "answer.txt", "..//", "ans.txt", "..//", "..//"};
    
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.check(args[0], new File(args[1])));
    assertEquals("java.io.FileNotFoundException: Директория не существует", thrown.toString());
    
    //Здесь file вляется директорией
    thrown = assertThrows(FileNotFoundException.class, () -> Task3.check(args[2], new File(args[3])));
    assertEquals("java.io.FileNotFoundException: Записываемый файл является директорией!", thrown.toString());
    
    thrown = assertThrows(FileNotFoundException.class, () -> Task3.check(args[4], new File(args[5])));
    assertEquals("java.io.FileNotFoundException: Записываемый файл является директорией!", thrown.toString());
  }
  
  @Test
  void fileFinderTest2() {
    
    String dir = "..//";
    File file = new File("answer.txt");
    boolean isWorking = true;
    
    try {
      Task3.fileFinder(dir, Task3.check(dir, file), 0);
    } catch (IOException thrown) {
      isWorking = false;
    }
    
    assertTrue(isWorking);
  }
  
  
}