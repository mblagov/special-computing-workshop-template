package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  void testNotExistingDirectoryInput() throws IOException {
    String[] args = new String[2];
    args[0] = "con";
    args[1] = "answer1.txt";
    File file = new File(args[1]);
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task3.walk(args[0], file, 0));
    assertEquals("java.io.FileNotFoundException: Директории не существует", thrown.toString());
  }

  @Test
  void testNotFileInput() throws IOException {
    String[] args = new String[2];
    args[0] = "con";
    args[1] = "answer.txt";
    File file = new File(args[1]);
    NotDirectoryException thrown = assertThrows(NotDirectoryException.class, () -> Task3.walk(args[0], file, 0));
    assertEquals("java.nio.file.NotDirectoryException: Записывающий файл является директорией!", thrown.toString());
  }

  @Test
  void testNormalInput() throws IOException {
    String[] args = new String[2];
    args[0] = "..//";
    args[1] = "answer1.txt";
    File file = new File(args[1]);
    boolean isEmpty = true;
    try {
      Task3.walk(args[0], file, 0);
    } catch (IOException thrown) {
      isEmpty = false;
    }
    assertTrue(isEmpty);
  }
}
