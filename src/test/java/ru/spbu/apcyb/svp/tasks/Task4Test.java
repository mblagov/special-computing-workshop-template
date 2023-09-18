package ru.spbu.apcyb.svp.tasks;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.spbu.apcyb.svp.tasks.task4.NumberGeneratorUtility;
import ru.spbu.apcyb.svp.tasks.task4.Task4;

/**
 * Тесты для задания 4.
 */
@TestInstance(Lifecycle.PER_CLASS)
class Task4Test {
  @Test
  void test1Task4() throws IOException {
    var filename = "./src/test/resources/test1.txt";
    NumberGeneratorUtility.generateSequence(filename, 1, 10);
    var scanner = new Scanner(new FileReader(filename)).useLocale(Locale.US);
    int counter = 0;
    while (scanner.hasNextDouble()) {
      counter++;
      scanner.nextDouble();
    }
    Assertions.assertEquals(10, counter);
  }

  @Test
  void test2Task4() throws IOException, ExecutionException, InterruptedException {
    var numbersFilename = "./src/test/resources/test1.txt";
    var answerFilename1 = "./src/test/resources/test2.txt";
    var answerFilename2 = "./src/test/resources/test3.txt";

    NumberGeneratorUtility.generateSequence(numbersFilename, 1, 10);
    Task4.countTan(numbersFilename, answerFilename1, answerFilename2, 10);
    var scanner1 = new Scanner(new FileReader(answerFilename1)).useLocale(Locale.US);
    var scanner2 = new Scanner(new FileReader(answerFilename2)).useLocale(Locale.US);
    int counter = 0;
    while (scanner1.hasNextDouble()) {
      counter++;
      scanner1.nextDouble();
    }
    Assertions.assertEquals(10, counter);
    counter = 0;
    while (scanner2.hasNextDouble()) {
      counter++;
      scanner2.nextDouble();
    }
    Assertions.assertEquals(10, counter);
  }
}
