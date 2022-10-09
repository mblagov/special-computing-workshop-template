package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  @DisplayName("parseStringTest")
  void parseStringTest() {
    Task1 testClass = new Task1();
    String input = "5 3 2";
    testClass.parseString(input);
    long expectedSum = 5;
    long[] expectedValues = new long[]{2, 3};
    assertAll(
        () -> Assertions.assertEquals(expectedSum, testClass.getSum()),
        () -> Assertions.assertArrayEquals(expectedValues, testClass.getValues())
    );


  }

  @ParameterizedTest
  @ValueSource(strings = {"1 a 3", "c 7 8"})
  @DisplayName("parseStringTest2")
  void parseStringTest2(String input) {
    Task1 testClass = new Task1();
    assertThrows(NumberFormatException.class,
        () -> testClass.parseString(
            input));

  }

  @ParameterizedTest
  @ValueSource(strings = {"-1 2 3", "1 2 -3", "1   "})
  @DisplayName("parseStringTest3")
  void parseStringTest3(String input) {
    Task1 testClass = new Task1();
    assertThrows(ArithmeticException.class, () ->
        testClass.parseString(input));
  }

  @Test
  @DisplayName("getNumsOfCombinationTest")
  void getNumsOfCombinationTest() {
    long[] testValues = new long[]{1, 2};
    final long expectedAns = 3;
    final long testSum = 4;

    Task1 testClass = new Task1();
    testClass.setSum(testSum);
    testClass.setValues(testValues);
    testClass.setNumOfCombinations(testClass.getSum(), testClass.getValues().length - 1,
        testClass.getValues());
    Assertions.assertEquals(expectedAns, testClass.getNumOfCombinations());
  }

  @Test
  @DisplayName("getCombinationsTest")
  void getCombinationsTest() throws FileNotFoundException {
    Task1 testClass = new Task1();
    long inputSum = 2;
    long[] inputValues = new long[]{1, 2};
    long[] firstExpected = new long[]{2};
    long[] secondExpected = new long[]{1, 1};
    testClass.setSum(inputSum);
    testClass.setValues(inputValues);
    try {
      testClass.loggerConfiguration();
    } catch (java.io.FileNotFoundException e) {
      throw new FileNotFoundException("logfile not found");
    }
    testClass.getCombinations(inputSum, inputValues.length - 1, inputValues);
    testClass.getLogger().getHandlers()[0].close();

    try {
      File file = new File("output.log");
      Scanner reader = new Scanner(file);
      reader.nextLine();
      String firstOutput = reader.nextLine().replaceAll("[A-Z:\\[\\],]", "").trim();
      String[] strOutput = firstOutput.split(" ");
      long[] firstAnswer = new long[strOutput.length];
      for (int i = 0; i < firstAnswer.length; i++) {
        firstAnswer[i] = Integer.parseInt(strOutput[i]);
      }
      reader.nextLine();
      String secondOutput = reader.nextLine().replaceAll("[A-Z:\\[\\],]", "").trim();
      strOutput = secondOutput.split(" ");
      long[] secondAnswer = new long[strOutput.length];
      for (int i = 0; i < secondAnswer.length; i++) {
        secondAnswer[i] = Integer.parseInt(strOutput[i]);
      }
      assertAll(
          () -> Assertions.assertArrayEquals(firstExpected, firstAnswer),
          () -> Assertions.assertArrayEquals(secondExpected, secondAnswer)
      );


    } catch (FileNotFoundException e) {
      testClass.getLogger().log(Level.SEVERE, e.toString());
    }
  }
}
