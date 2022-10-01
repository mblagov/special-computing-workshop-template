package ru.spbu.apcyb.svp.tasks;

import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Задание 1. Банкомат.
 */

public class Task1 {

  /**
   * Упорядочим массив в порядке убывания.
   */

  public static int[] ord(int[] arr) {
    for (int j = 1; j < arr.length; j++) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[j] > arr[i]) {
          int tmp = arr[j];
          arr[j] = arr[i];
          arr[i] = tmp;
        }
      }
    }
    return arr;
  }

  /**
   * Функция для получения комбинаций купюр данной суммы.
   */
  public static int combinations(int sum, int prevNominal, String values, int[] arr) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    int count = 0;
    if (sum == 0) {
      count++;
      logger.info(values);
    }
    for (int j : arr) {
      if ((prevNominal >= j) && (sum >= j)) {
        count += combinations(sum - j, j, values + " " + j + " ", arr);
      }
    }
    return count;
  }

  /**
   * Функция для ввода суммы.
   */

  public  static int sumIn(String str) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    int sum;
    try {
      sum = Integer.parseInt(str);
    } catch (Exception e) {
      logger.info("- ОШИБКА - поймали Exception при вводе суммы");
      return -1;
    }
    if (sum > 0) {
      return sum;
    } else {
      logger.info("- ОШИБКА - сумма < 0 ?!");
      return -1;
    }
  }

  /**
   * Функция для ввода номиналов.
   */

  public  static int[] nomIn(String str) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    String[] strMas = str.split(" ");
    int[] arr = new int[strMas.length];
    for (int i = 0; i < arr.length; i++) {
      try {
        arr[i] = Integer.parseInt(strMas[i]);
        if (arr[i] <= 0) {
          logger.info("- ОШИБКА - таких купюр не бывает");
          return new int[0];
        }
      } catch (NumberFormatException nfe) {
        logger.info(" - ОШИБКА - поймали Exception при вводе номиналов");
        return new int[0];
      }
    }
    return ord(arr);
  }

  /**
   * Main  - мать всего программирования без нее никуда.
   * */

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    Scanner scanner = new Scanner(System.in);
    logger.info("Введите сумму - >");
    String str1 = scanner.nextLine();
    logger.info("Введите номиналы купюр - > ");
    String str2 = scanner.nextLine();
    int count = combinations(sumIn(str1), nomIn(str2)[0], " ", nomIn(str2));
    logger.info("Всего комбинаций: ");
    String str = String.valueOf(count);
    logger.info(str);
  }
}