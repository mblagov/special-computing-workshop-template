package ru.spbu.apcyb.svp.tasks;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Задание 1. Банкомат.
 */
public class Task1 {

  /**
   * Метод для получения комбинаций купюр данной суммы.
   */
  public static int combinations(int sum, int prevNominal, String combination, int[] nomArray) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    int count = 0;
    if (sum == 0) {
      count++;
      logger.info(combination);
    }
    for (int j : nomArray) {
      if ((prevNominal >= j) && (sum >= j)) {
        count += combinations(sum - j, j, combination + " " + j + " ", nomArray);
      }
    }
    return count;
  }

  /**
   * Метод для ввода суммы.
   */
  public static int sum(String str) {
    int sum;
    try {
      sum = parseInt(str);
    } catch (Exception e) {
      throw new IllegalArgumentException("Ошибка при вводе суммы");
    }
    if (sum > 0) {
      return sum;
    } else {
      throw new ArithmeticException("Ошибка при вводе суммы");
    }
  }

  /**
   * Метод для ввода номиналов.
   */
  public static int[] nomIn(String str) {
    String[] strMas = str.split(" ");
    int[] nomArr = new int[strMas.length];
    for (int i = 0; i < nomArr.length; i++) {
      try {
        nomArr[i] = parseInt(strMas[i]);
        if (nomArr[i] <= 0) {
          throw new ArithmeticException("Ошибка при вводе номиналов");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Ошибка при вводе номиналов");
      }
    }
    Arrays.sort(nomArr);
    return Arrays.stream(nomArr).distinct().toArray();
  }

  /**
   * Main.
   */
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    Scanner scanner = new Scanner(System.in);
    String sum = scanner.nextLine();
    String nominal = scanner.nextLine();
    String str = String.valueOf(combinations(sum(sum), nomIn(nominal)[0], " ", nomIn(nominal)));
    logger.info(str);
  }
}