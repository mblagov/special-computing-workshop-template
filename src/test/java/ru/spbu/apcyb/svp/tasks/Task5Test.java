package ru.spbu.apcyb.svp.tasks;

/**
 * Тесты для задания 5.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Task5Test {

  private static final Task5 proc = new Task5();
  private static final String bookPath =
      ".\\src\\test\\resources\\num5\\WarAndPeace.txt";
  private static final String directoryPath =
      ".\\src\\test\\resources\\num5\\WordFiles";
  private static final String countPath =
      ".\\src\\test\\resources\\num5\\Counts.txt";
  private static final String smallTextPath =
      ".\\src\\test\\resources\\num5\\SmallTextTest.txt";

  @BeforeAll
  static void init() {
    for (File file : Objects.requireNonNull(Paths.get(directoryPath).toFile().listFiles())) {
      if (!file.isDirectory()) {
        file.delete();
      }
    }
    try {
      new PrintWriter(Paths.get(countPath).toString()).close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void readAndCountTest() {
    try {
      proc.readAndCount(Paths.get(smallTextPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String expected = "bar = 1\nfoo = 3\nbaz = 1";
    assertEquals(expected, proc.mapToString());
  }

  @Test
  public void bigTextTest() {
    try {
      proc.readAndCount(Paths.get(bookPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      proc.printCount(Paths.get(countPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      proc.parallelFileCount(Paths.get(directoryPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}