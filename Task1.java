package ru.spbu.apcyb.svp.tasks;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * Задание 1.
 */
public class Task1 {
  public static final Logger logger = Logger.getLogger(Task1.class.getName());
  public static final Scanner scan = new Scanner(System.in);

  /**
   * Перевод строки в сумму
   */
  public static long strToLong(String string) {
    long num;
    try {
      num = Long.parseLong(string);
    } catch (Exception e) {
      throw new IllegalArgumentException("Вы ввели не целое число.");
    }
    if (num > 0) {
      return num;
    } else {
      throw new ArithmeticException("Вы ввели не натуральное число.");
    }
  }

  /**
   * Ввод суммы
   */

  public static long inputSum() {
    logger.info("Введите сумму: ");
    String string = scan.nextLine();
    return strToLong(string);
  }

  /**
   * Перевод строки в массив номиналов
   */

  public static Long[] strToArray(String string) {
    ArrayList < Long > nominals = new ArrayList < > ();
    String[] strNumbers = string.split(" ");
    for (String tmp: strNumbers) {
      nominals.add(strToLong(tmp));
    }
    Set < Long > set = new HashSet < > (nominals);
    nominals.clear();
    nominals.addAll(set);
    nominals.sort(Collections.reverseOrder());
    return nominals.toArray(new Long[0]);
  }

  /**
   * Ввод номиналов
   */
  public static Long[] inputNominals() {
    logger.info("Введите номиналы через пробел: ");
    String string = scan.nextLine();
    return strToArray(string);

  }

  /**
   * Подсчёт комбинаций
   */

  public static int combinations(long sum, Long[] nominals, String str, int index) {
    int count = 0;
    if (nominals.length == index + 1) {

      if (sum % nominals[index] == 0) {
        count++;
        str += ((nominals[index]) + " ").repeat((int)(sum / (nominals[index])));
        logger.info(str);
      }

      return count;
    }

    for (int i = index; i <= sum / nominals[index]; i++) {

      count += combinations(sum - i * nominals[index], nominals, str + ((nominals[index]) + " ").repeat(i), index + 1);

    }

    return count;
  }

  public static void main(String[] args) {
    long sum = inputSum();
    Long[] nominals = inputNominals();
    logger.info("Комбинации:");
    int count = combinations(sum, nominals, "", 0);
    Supplier < String > strSupplier = () -> ("Количество комбинаций: " + count);
    logger.info(strSupplier);

  }
}