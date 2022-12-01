package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {
  
  @Test
  void testInput1() throws IOException {
  
    String path = "";
    String file = "answer.txt";
    
    try (FileWriter writer = new FileWriter(file, false)) {
      Task3.walk(path, writer, 0);
    } catch (FileNotFoundException thrown) {
      assertEquals("java.io.FileNotFoundException", thrown.toString());
    }
    
  }
  
  @Test
  void testInput2() throws IOException {
  
    String path = "FFF";
    String file = "answer.txt";
    
    try (FileWriter writer = new FileWriter(file, false)) {
      Task3.walk(path, writer, 0);
    } catch (FileNotFoundException thrown) {
      assertEquals("java.io.FileNotFoundException", thrown.toString());
    }
    
  }
}