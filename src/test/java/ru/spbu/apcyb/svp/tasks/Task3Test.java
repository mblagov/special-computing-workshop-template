package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task3.*;

/**
 * Тесты для задания 3.
 */
class Task3Test {

  @Test
  void worksCorrectly() throws IOException{
    String dirPath = "src\\test";
    String outPath = "src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\task3\\Task3.out";
    Task3.main(new String[]{dirPath, outPath});
    String[] EXPECTED = {
        "test:",
        "    java:",
        "        ru:",
        "            spbu:",
        "                apcyb:",
        "                    svp:",
        "                        tasks:",
        "                            ArrayUtilsTest.java",
        "                            Task1Test.java",
        "                            Task2Test.java",
        "                            Task3Test.java",
        "                            Task4Test.java",
        "                            Task5Test.java",
        "    resources:",
        "        .stub"
    };

    Scanner scanner = new Scanner(new File(outPath));
    scanner.useDelimiter("\n");

    Assertions.assertTrue(scanner.hasNext());
    for (String line : EXPECTED) {
      Assertions.assertEquals(line, scanner.next());
    }
  }

  @Test
  void correctException1() {
    String dirPath = "src\\tests";
    String outPath = "src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\task3\\Task3.out";
    Assertions.assertThrowsExactly(IOException.class, () -> {
      Task3.main(new String[]{dirPath, outPath});
    });
  }

  @Test
  void correctException2() {
    String dirPath = "src\\test";
    String outPath = "src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\task\\Task3.out";
    Assertions.assertThrowsExactly(FileNotFoundException.class, () -> {
      Task3.main(new String[]{dirPath, outPath});
    });
  }
}
