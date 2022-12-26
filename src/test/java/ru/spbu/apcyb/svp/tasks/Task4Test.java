package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 4.
 */
class Task4Test {
  @Test
  void inputFileNotExists() {
    assertThrows(
        FileNotFoundException.class,
        () -> Task4.multiThreadTan("afjafja1fanfa", "testfile", 1, 1)
    );
  }

  @Test
  void fileInInexistingFolder() {
    assertThrows(
        FileNotFoundException.class,
        () -> Task4.multiThreadTan("numbers", "/a/b/c/d/e/f", 1, 1)
    );
  }

  @Test
  void normalRun() {
    assertDoesNotThrow(
        () -> Task4.multiThreadTan("numbers", "testfile", 10000, 10)
    );
  }

}
