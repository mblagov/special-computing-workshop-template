package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 4.
 */
class Task4Test {
  
  @Test
  void testException1() {
    
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class,
      () -> Task4.singleThread(new FileWriter("error.txt", false), 100));
    
    assertEquals("Файл.txt не был найден.", thrown.getMessage());
  }
  
  @Test
  void testException2() throws IOException {
    
    try {
      FileWriter fw = new FileWriter("error1.txt", false);
    } catch (FileNotFoundException e) {
      assertEquals("error1.txt (Is a directory)", e.getMessage());
    }
  }
  
  @Test
  void testSingleThread() throws IOException {
    
    Task4.singleThread(new FileWriter("SingleTreads.txt", false), 1000);
    BufferedReader xFile = new BufferedReader(new FileReader("x.txt"));
    BufferedReader singleFile = new BufferedReader(new FileReader("SingleThread.txt"));
    boolean result = true;
    
    for (int i = 0; i < 1000; i++) {
      if (Math.tan(Double.parseDouble(xFile.readLine())) != Double.parseDouble(singleFile.readLine())) {
        result = false;
        break;
      }
    }
    
    assertTrue(result);
  }
  
  @Test
  void testMultiThread() throws IOException, ExecutionException, InterruptedException {

    Task4.multiThread(new FileWriter("MultiRes.txt", false), 1000, 10);
    BufferedReader xFile = new BufferedReader(new FileReader("x.txt"));
    BufferedReader multiFile = new BufferedReader(new FileReader("MultiThread.txt"));
    boolean result = true;
    
    for (int i = 0; i < 100; i++) {
      if (Math.tan(Double.parseDouble(xFile.readLine())) != Double.parseDouble(multiFile.readLine())) {
        result = false;
        break;
      }
    }
    assertTrue(result);
  }
}
