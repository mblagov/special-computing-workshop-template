package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Задание 1.
 */
public class Task1 {

  /**
   * Функция нахождения комбинаций.
   */
  public static int variants(Long[] denom, long sum, long min, String str) {
    int count = 0;
    if (sum == 0) {
      Logger logger = Logger.getLogger(Task1.class.getName());
      logger.info(str);
      count++;
      return count;
    }
    ArrayList<Long> newDenom = new ArrayList<>(Arrays.asList(denom));
    for (long num : denom) {
      if (sum - num < num) {
        newDenom.remove(0);
      }
      if ((min >= num) && (sum >= num)) {
        Long[] tmp = newDenom.toArray(new Long[0]);
        count += variants(tmp, sum - num, num, str + num + " ");
      }
    }
    return count;
  }

  /**
   * Строка в массив.
   */
  public static Long[] inDenom(String str) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    ArrayList<Long> denom = new ArrayList<>();
    int numBeg = 0;
    int numEnd = 0;
    while (numEnd < str.length()) {
      if (str.charAt(numBeg) == ' ') {
        numBeg++;
      }
      for (numEnd = numBeg; (str.length() > numEnd) && (str.charAt(numEnd) != ' '); ) {
        numEnd++;
      }
      String tmp = str.substring(numBeg, numEnd);
      numBeg = numEnd;
      try {
        denom.add(Long.parseLong(tmp));
        if (denom.get(denom.size() - 1) <= 0) {
          return new Long[0];
        }
      } catch (NumberFormatException e) {
        logger.info("Неверное значение.");
        return new Long[0];
      }
    }
    Set<Long> set = new HashSet<>(denom);
    denom.clear();
    denom.addAll(set);
    Collections.sort(denom, Collections.reverseOrder());
    return denom.toArray(new Long[0]);
  }

  /**
   * Разложение строки на массив номиналов.
   */
  public static Long[] inputDenom() {
    Scanner in = new Scanner(System.in);
    Logger logger = Logger.getLogger(Task1.class.getName());
    logger.info("Введите номиналы через пробел: ");
    String str = in.nextLine();
    return inDenom(str);
  }

  /**
   * Строка в сумму.
   */
  public static long inSum(String str) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    try {
      return Long.parseLong(str);
    } catch (NumberFormatException e) {
      logger.info("Неверное значение.");
      return -1;
    }
  }

  /**
   * Ввод суммы.
   */
  public static long inputSum() {
    Scanner in = new Scanner(System.in);
    Logger logger = Logger.getLogger(Task1.class.getName());
    logger.info("Введите сумму: ");
    String str = in.nextLine();
    return inSum(str);
  }

  /**
   * (Это точно нужно комментировать?)Просто мейн.
   */
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Task1.class.getName());
    long sum = 0;
    while (sum <= 0) {
      sum = inputSum();
    }
    Long[] denom = inputDenom();
    while (denom.length == 0) {
      denom = inputDenom();
    }
    logger.info("Комбинации:");
    int count = variants(denom, sum, denom[0], "");
    Supplier<String> strSupplier = () -> ("Количество комбинаций: " + count);
    logger.info(strSupplier);
  }
}
