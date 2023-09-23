package ru.spbu.apcyb.svp.tasks.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * This is the main class. It has methods for initial processing of input data.
 *
 * @author Evgeny
 */

public class Task1 {

  public static final Logger logger = Logger.getLogger(Task1.class.getName());

  /**
   * Here start point of the program.
   *
   * @param args command line values
   */
  public static void main(String[] args) {
    Task1 task1 = new Task1();
    SumExchange sumExchange = task1.splitAndSortData();
    Combinations combinations = sumExchange.allCombinationsOfExchanges();
    task1.outputWithLogger(combinations);
  }

  /**
   * All data is entered in one line.
   *
   * @return the entered data in the form of a string
   */

  public String inputData() {
    String lineInput = "";
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

      lineInput = bufferedReader.readLine();

    } catch (IOException e) {
      logger.warning(e.toString());
    }
    return lineInput;
  }

  /**
   * The method receives a string as input and converts it to a list Integer.
   *
   * @return list Integer
   */

  public List<Integer> transformInputLineToIntegerList() {
    String lineInput = inputData();

    if (lineInput == null) {
      throw new NullPointerException("Ошибка ввода");
    }
    String[] arrayOfStringNumber = lineInput.trim().split(" ");

    if (arrayOfStringNumber.length < 2) {
      throw new ArrayIndexOutOfBoundsException("Введено недостаточно цифр");
    }
    return Arrays.stream(arrayOfStringNumber)
        .map(Integer::parseInt)
        .toList();
  }

  /**
   * The method divides the data into an amount and a set of nominal coins.
   * <br>
   * And sorts the nominal coins.
   *
   * @return an object containing the amount and denomination
   * @see SumExchange
   */

  public SumExchange splitAndSortData() {
    List<Integer> arrayOfNumber = transformInputLineToIntegerList();
    List<Integer> denominations = new ArrayList<>(arrayOfNumber);

    int sum = arrayOfNumber.get(0);
    denominations.remove(0);
    if (sum < 0) {
      throw new ArithmeticException("Сумма меньше нуля");
    }

    Collections.sort(denominations);
    denominations = denominations.stream().distinct().toList();
    return new SumExchange(sum, denominations);
  }

  private void outputWithLogger(Combinations combinations) {
    String listSize = String.valueOf(combinations.getListOfCombinations().size());
    logger.info(listSize);

    for (Combination combination : combinations.getListOfCombinations()) {
      String stringCombination = combination.toString();
      logger.info(stringCombination);
    }
  }
}