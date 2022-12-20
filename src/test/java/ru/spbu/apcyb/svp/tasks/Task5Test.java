package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
class Task5Test {
  /**
   * В тесте проводится проверка на наличие файла.
   */
  @Test
  void testNotExistingFile() throws IOException {
    FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> Task5.readFile("con.txt"));
    assertEquals("Файл не найден.", thrown.getMessage());
  }

  @Test
  void testNormalInput() throws IOException {
    boolean res = true;
    try {
      Task5.main();
    } catch (IOException thrown) {
      res = false;
    }
    assertTrue(res);
  }
}
