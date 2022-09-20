package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
class Task1Test {

  @Test
  @DisplayName("parsestringTest")
  void parsestringTest() {
    Task1 testClass = new Task1();
    String input = "5 3 2";
    testClass.parseString(input);
    int expectedSum = 5;
    int[] expectedValues = new int[]{2, 3};
    Assertions.assertEquals(expectedSum, testClass.getSum());
    Assertions.assertArrayEquals(expectedValues, testClass.getValues());

  }

  @Test
  @DisplayName("parsestringTest2")
  void parsestringTest2() {
    Task1 testclass = new Task1();
    String inputFirst = "1  a  3";
    String inputSecond = "c   7   8";
    Exception firstException = assertThrows(NumberFormatException.class,
        () -> testclass.parseString(
            inputFirst));
    Exception secondException = assertThrows(NumberFormatException.class,
        () -> testclass.parseString(
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
    Task1 testclass = new Task1();
    String inputFirst = "-1 2 3";
    String inputSecond = "1 2 -3";
    Exception firstException = assertThrows(ArithmeticException.class, () ->
        testclass.parseString(inputFirst));
    Exception secondException = assertThrows(ArithmeticException.class, () ->
        testclass.parseString(inputSecond));
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

    Task1 testclass = new Task1();
    testclass.setSum(testSum);
    testclass.setValues(testValues);
    testclass.getNumsOfCombinations(testclass.getSum(), testclass.getValues().length - 1,
        testclass.getValues());
    Assertions.assertEquals(expectedAns, testclass.getAns());
  }

  @Test
  @DisplayName("getCombinationsTest")
  void getCombinationsTest() {
    Task1 testclass = new Task1();
    int inputSum = 2;
    int[] inputValues = new int[]{1, 2};
    int[] firstExpected = new int[]{2};
    int[] secondExpected = new int[]{1, 1};
    testclass.setSum(inputSum);
    testclass.setValues(inputValues);
    testclass.loggerConfiguration();
    testclass.getCombinations(inputSum, inputValues.length - 1, inputValues);
    testclass.getLogger().getHandlers()[0].close();

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
      e.printStackTrace();
    }


  }


}
