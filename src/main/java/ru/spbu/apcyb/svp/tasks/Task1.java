package ru.spbu.apcyb.svp.tasks;

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
   *
   * @param sum - переменная для передачи суммы
   * @param prevNominal - переменная для хранения предыдущего номинала, участвующего в размене.
   * @param combination - строка для хранения и вывода комбинации
   * @param nomArr - массив номиналов
   * @return число комбинаций
   */
  public static int combinations(int sum, int prevNominal, String combination, Integer[] nomArr) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    int count = 0;
    if (sum == 0) {
      count++;
      logger.info(combination);
    }
    for (int j : nomArr) {
      if ((prevNominal >= j) && (sum >= j)) {
        count += combinations(sum - j, j, combination + " " + j + " ", nomArr);
      }
    }
    return count;
  }

  /**
   * Метод для ввода суммы.
   *
   * @param str - строка для ввода суммы
   * @return сумма
   */

  public  static int sumIn(String str) {
    int sum;
    try {
      sum = Integer.parseInt(str);
    } catch (Exception e) {
      throw new IllegalArgumentException(" - ОШИБКА - поймали Exception при вводе суммы");
    }
    if (sum > 0) {
      return sum;
    } else {
      throw new ArithmeticException(" - ОШИБКА - поймали Exception при вводе суммы");
    }
  }

  /**
   * Метод для ввода номиналов.
   *
   * @param str - строка с номиналами, введеными с консоли
   * @return массив номиналов
   */

  public  static Integer[] nomIn(String str) {
    String[] strMas = str.split(" ");
    Integer[] nomArr = new Integer[strMas.length];
    for (int i = 0; i < nomArr.length; i++) {
      try {
        nomArr[i] = Integer.parseInt(strMas[i]);
        if (nomArr[i] <= 0) {
          throw new ArithmeticException(" - ОШИБКА - поймали Exception при вводе номиналов");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException(" - ОШИБКА - поймали Exception при вводе номиналов");
      }
    }
    Arrays.sort(nomArr, Collections.reverseOrder());
    return (nomArr);
  }

  /**
   * Main.
   * программа принимает от пользователя две строки из консоли
   * str1 - сумма
   * str2 - номиналы через пробел
   * выводит комбинации и их число
   */

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    Scanner scanner = new Scanner(System.in);
    String sum = scanner.nextLine();
    String nominal = scanner.nextLine();
    String str = String.valueOf(combinations(sumIn(sum), nomIn(nominal)[0], " ", nomIn(nominal)));
    logger.info(str);
  }
}