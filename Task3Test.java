package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Тесты для задания 3.
 */
class Task3Test {
  @Test
  void findFile1() {
    String[] args = {"NotExist", "res.txt"};
    FileNotFoundException except = assertThrows(FileNotFoundException.class,
        () -> Task3.findFiles(args[0], new FileWriter(new File(args[1]), false), 0));
    assertEquals("java.io.FileNotFoundException: Директория не существует", except.toString());

  }
  @Test
  void findFile2() {
    String[] args = {".\\src\\main", ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\"};
    FileNotFoundException except = assertThrows(FileNotFoundException.class, () -> Task3.main(args));
    assertEquals("java.io.FileNotFoundException: Целевой файл является директорией", except.toString());

  }
  @Test
  void findFile3() throws IOException {
    String[] args = {".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\", ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\res.txt"};
    Task3.main(args);
    File actual = new File(".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\res.txt");
    File expected = new File(".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\expected.txt");
    Assertions.assertTrue(FileUtils.contentEquals(actual, expected));

  }

}