package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для задания 5.
 */
public class Task5Test {

 @Test
 void testNormalInput() throws Exception {
   Exception thrown = assertThrows(Exception.class, Task5::start);
   
   assertEquals("java.io.FileNotFoundException: Файл не был найден!, попробуйте снова!", thrown.getMessage());
 }
 
}
