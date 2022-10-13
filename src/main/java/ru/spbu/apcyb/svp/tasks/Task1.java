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

  public static final Logger logger = Logger.getLogger(Task1.class.getName());
  public static final Scanner in = new Scanner(System.in);

  /**
   * Функция нахождения комбинаций.
   */
  public static int variants(Long[] notes, long sum, long min, String str) {
    int count = 0;
    if (sum == 0) {
      logger.info(str);
      count++;
      return count;
    }
    ArrayList<Long> newNotes = new ArrayList<>(Arrays.asList(notes));
    for (long num : notes) {
      if (sum - num < num) {
        newNotes.remove(0);
      }
      if ((min >= num) && (sum >= num)) {
        Long[] tmp = newNotes.toArray(new Long[0]);
        count += variants(tmp, sum - num, num, str + num + " ");
      }
    }
    return count;
  }

  /**
   * Строка в массив.
   */
  public static Long[] strToLongArray(String str) {
    ArrayList<Long> notes = new ArrayList<>();
    String[] substrings = str.split(" ");
    for (String tmp : substrings) {
      notes.add(strToLong(tmp));
    }
    Set<Long> set = new HashSet<>(notes);
    notes.clear();
    notes.addAll(set);
    notes.sort(Collections.reverseOrder());
    return notes.toArray(new Long[0]);
  }

  /**
   * Разложение строки на массив номиналов.
   */
  public static Long[] inputNotes() {
    logger.info("Введите номиналы через пробел: ");
    String str = in.nextLine();
    return strToLongArray(str);
  }

  /**
   * Строка в сумму.
   */
  public static long strToLong(String str) {
    long result;
    try {
      result = Long.parseLong(str);
    } catch (Exception e) {
      throw new IllegalArgumentException("Ошибка - не целое число!");
    }
    if (result > 0) {
      return result;
    } else {
      throw new ArithmeticException("Ошибка - не натуральное число!");
    }
  }

  /**
   * Ввод суммы.
   */
  public static long inputSum() {
    logger.info("Введите сумму: ");
    String str = in.nextLine();
    return strToLong(str);
  }

  /**
   * (Это точно нужно комментировать?)Просто мейн.
   */
  public static void main(String[] args) {
    long sum = inputSum();
    Long[] notes = inputNotes();
    logger.info("Комбинации:");
    int count = variants(notes, sum, notes[0], "");
    Supplier<String> strSupplier = () -> ("Количество комбинаций: " + count);
    logger.info(strSupplier);
  }
}
