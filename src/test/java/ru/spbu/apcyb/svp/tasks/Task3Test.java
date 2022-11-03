package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Тесты для задания 3.
 */
class Task3Test {

  static class LogHandler extends Handler {

    Level lastLevel = Level.FINEST;

    public Level checkLevel() {
      return lastLevel;
    }

    public void publish(LogRecord record) {
      lastLevel = record.getLevel();
    }

    public void close() {
    }

    public void flush() {
    }
  }

  @Test
  void testMain() {
    String expected = "test\n"
        + "-testFile1.txt\n"
        + "-testFolder1\n"
        + "--testFolder1File1.txt\n"
        + "-testFolder2\n"
        + "--testFolder2File1.txt\n"
        + "--testFolder2Folder1\n"
        + "--testFolder2Folder2\n"
        + "---testFolder2Folder2File1.txt\n"
        + "---testFolder2Folder2File2.txt\n";
    String[] args = new String[]{"C:\\Test\\test", "C:\\Test\\test.txt"};
    Task3.main(args);
    StringBuilder actual = new StringBuilder();
    try (FileReader reader = new FileReader("C:\\Test\\test.txt")) {
      int c;
      while ((c = reader.read()) != -1) {
        actual.append((char) c);
      }
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
    }
    Assertions.assertEquals(expected, actual.toString());
  }

  @Test
  void testError() {
    LogHandler handler = new LogHandler();
    handler.setLevel(Level.ALL);
    Task3.logger.setUseParentHandlers(false);
    Task3.logger.addHandler(handler);
    Task3.logger.setLevel(Level.ALL);
    String[] args = new String[]{"C:\\Test\\test", "C:\\Test\\test"};
    Task3.main(args);
    Assertions.assertEquals(Level.INFO, handler.checkLevel());
  }
}

