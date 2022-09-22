package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    int expectedSum = 5;
    int[] expectedValues = new int[]{2, 3};
    Assertions.assertEquals(expectedSum, testClass.getSum());
    Assertions.assertArrayEquals(expectedValues, testClass.getValues());

  }

  @Test
  @DisplayName("parseStringTest2")
  void parseStringTest2() {
    Task1 testClass = new Task1();
    String inputFirst = "1  a  3";
    String inputSecond = "c   7   8";
    Exception firstException = assertThrows(NumberFormatException.class,
        () -> testClass.parseString(
            inputFirst));
    Exception secondException = assertThrows(NumberFormatException.class,
        () -> testClass.parseString(
            inputSecond));

    String expectedMessage = "Input error";
    String firstActualMessage = firstException.getMessage();
    String secondActualMessage = secondException.getMessage();

    assertAll(
        () -> assertTrue(firstActualMessage.contains(expectedMessage)),
        () -> assertTrue(secondActualMessage.contains(expectedMessage))
    );
  }

  @Test
  @DisplayName("parseStringTest3")
  void parseStringTest3() {
    Task1 testClass = new Task1();
    String inputFirst = "-1 2 3";
    String inputSecond = "1 2 -3";
    Exception firstException = assertThrows(ArithmeticException.class, () ->
        testClass.parseString(inputFirst));
    Exception secondException = assertThrows(ArithmeticException.class, () ->
        testClass.parseString(inputSecond));
    String expectedMessage = "invalid";
    String firstActualMessage = firstException.getMessage();
    String secondActualMessage = secondException.getMessage();
    assertAll(
        () -> assertTrue(firstActualMessage.contains(expectedMessage)),
        () -> assertTrue(secondActualMessage.contains(expectedMessage))
    );

  }

  @Test
  @DisplayName("getNumsOfCombinationTest")
  void getNumsOfCombinationTest() {
    int[] testValues = new int[]{1, 2};
    final int expectedAns = 3;
    final int testSum = 4;

    Task1 testClass = new Task1();
    testClass.setSum(testSum);
    testClass.setValues(testValues);
    testClass.getNumsOfCombinations(testClass.getSum(), testClass.getValues().length - 1,
        testClass.getValues());
    Assertions.assertEquals(expectedAns, testClass.getAns());
  }

  @Test
  @DisplayName("getCombinationsTest")
  void getCombinationsTest() {
    Task1 testClass = new Task1();
    int inputSum = 2;
    int[] inputValues = new int[]{1, 2};
    int[] firstExpected = new int[]{2};
    int[] secondExpected = new int[]{1, 1};
    testClass.setSum(inputSum);
    testClass.setValues(inputValues);
    testClass.loggerConfiguration();
    testClass.getCombinations(inputSum, inputValues.length - 1, inputValues);
    testClass.getLogger().getHandlers()[0].close();

    try {
      File file = new File("output.log");
      Scanner reader = new Scanner(file);
      reader.nextLine();
      String firstOutput = reader.nextLine().replaceAll("[A-Z:\\[\\],]", "").trim();
      String[] strOutput = firstOutput.split(" ");
      int[] firstAnswer = new int[strOutput.length];
      for (int i = 0; i < firstAnswer.length; i++) {
        firstAnswer[i] = Integer.parseInt(strOutput[i]);
      }
      reader.nextLine();
      String secondOutput = reader.nextLine().replaceAll("[A-Z:\\[\\],]", "").trim();
      strOutput = secondOutput.split(" ");
      int[] secondAnswer = new int[strOutput.length];
      for (int i = 0; i < secondAnswer.length; i++) {
        secondAnswer[i] = Integer.parseInt(strOutput[i]);
      }

      Assertions.assertArrayEquals(firstExpected, firstAnswer);
      Assertions.assertArrayEquals(secondExpected, secondAnswer);


    } catch (FileNotFoundException e) {
      testClass.getLogger().log(Level.INFO, e.toString());
    }


  }


}
