package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import ru.spbu.apcyb.svp.tasks.task4.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 4.
 */
class Task4Test {

  String numsPath = "src/main/resources/nums.txt";
  String outPath1 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/SingleThreadedTanOutput.txt";
  String outPath2 = "src/main/java/ru/spbu/apcyb/svp/tasks/task4/MultiThreadedTanOutput.txt";

  @Test
  void singleThreadedOutputSizeCheck() throws FileNotFoundException {
    long expected = 10000;
    String path = this.outPath1;
    SingleThreadedTan singleThreadedTan = new SingleThreadedTan(this.numsPath, path, expected);
    singleThreadedTan.compute();
    try (Scanner scanner = new Scanner(new File(path))) {
      scanner.useDelimiter("\n");
      long actual = 0;
      while (scanner.hasNext()) {
        actual++;
        scanner.next();
      }
      Assertions.assertEquals(expected, actual);
    }
  }

  @Test
  void multiThreadedOutputSizeCheck() throws IOException {
    long expected = 10000;
    String path = this.outPath2;
    MultiThreadedTan multiThreadedTan = new MultiThreadedTan(this.numsPath, path, expected);
    multiThreadedTan.compute();
    try (Scanner scanner = new Scanner(new File(path))) {
      scanner.useDelimiter("\n");
      long actual = 0;
      while (scanner.hasNext()) {
        actual++;
        scanner.next();
      }
      Assertions.assertEquals(expected, actual);
    }
  }

}
