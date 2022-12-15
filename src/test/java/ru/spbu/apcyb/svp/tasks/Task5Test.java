package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для задания 5.
 */
public class Task5Test {

 @Test
 void test() {
   boolean thrown = true;
  
   try {
     Task5.start();
   } catch (Exception e) {
     thrown = false;
   }
  
   assertTrue(thrown);
 }
 
}
