package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  /**
   * В тесте проводится проверка на обработку из файла, при этом передаем директорию.
   */
  @Test
  void testNotFile() throws IOException {
    IOException thrown = assertThrows(IOException.class, () -> Task5.main(
        new String[]{"text1.txt", "input.txt"}));
    assertEquals("text1.txt (Is a directory).", thrown.getMessage());
  }

  /**
   * В тесте проводится проверка на корректное выполнение программы.
   */
  @Test
  void testNormalInput() throws IOException {
    IOException thrown = assertThrows(IOException.class, () -> Task5.main(
        new String[]{"counts.txt", "input123.txt"}));
    assertEquals("Something went wrong.", thrown.getMessage());
  }
}
