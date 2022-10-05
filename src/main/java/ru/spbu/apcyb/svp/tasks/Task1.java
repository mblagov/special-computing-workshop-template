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
  public static Long[] inNotes(String str) {
    ArrayList<Long> notes = new ArrayList<>();
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
        notes.add(Long.parseLong(tmp));
        if (notes.get(notes.size() - 1) <= 0) {
          return new Long[0];
        }
      } catch (NumberFormatException e) {
        return new Long[0];
      }
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
    return inNotes(str);
  }

  /**
   * Строка в сумму.
   */
  public static long inSum(String str) {
    try {
      return Long.parseLong(str);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Ввод суммы.
   */
  public static long inputSum() {
    logger.info("Введите сумму: ");
    String str = in.nextLine();
    return inSum(str);
  }

  /**
   * (Это точно нужно комментировать?)Просто мейн.
   */
  public static void main(String[] args) {
    long sum = inputSum();
    Long[] notes = inputNotes();
    if (sum > 0 && notes.length > 0) {
      logger.info("Комбинации:");
      int count = variants(notes, sum, notes[0], "");
      Supplier<String> strSupplier = () -> ("Количество комбинаций: " + count);
      logger.info(strSupplier);
    } else {
      logger.info("Ошибка ввода!");
    }
  }
}
