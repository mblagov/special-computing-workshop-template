package ru.spbu.apcyb.svp.tasks;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
class Task5Test {


  @Test
  void testNotExistingFile() throws IOException {
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task5.readFile("notExist.txt"));
    assertEquals("Файл не найден", thrown.getMessage());
  }

  @Test
  void sthWentWrong() throws IOException {
    String s1 = "counts.txt";
    String s2 = "xd.txt";
    IOException thrown = assertThrows(IOException.class, () -> Task5.run(s1, s2));
    assertEquals("Что-то пошло не так", thrown.getMessage());
  }

}