package ru.spbu.apcyb.svp.tasks;

/**
 * Тесты для задания 3.
 */

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class Task3Test {

  @Test
  public void checkMainMethod() {
    try {
      Task3.main(new String[]{"nofolder", "nofile.txt"});
    } catch (Exception e) {
      fail("There is no such folder: nofolder" + e);
    }
  }

  @Test
  public void checkListFilesMethod() {
    File testDir = new File(".\\src\\test\\resources\\num3");
    Path testWriteFile = Paths
        .get(".\\src\\test\\resources\\num3\\test.txt");
    try {
      assertTrue(Task3.listFiles(testDir, testWriteFile, 0));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
