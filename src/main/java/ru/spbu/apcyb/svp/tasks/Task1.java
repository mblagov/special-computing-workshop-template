package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Задание 1.
 */

public class Task1 {

  static Logger logger = Logger.getLogger(Task1.class.getName());

  /**
   * Стартовая функция.
   */
  public static void main(String[] args) {
    logger.info("Введите сумму для размена:");
    final long sum = inputSum();

    logger.info("Введите номиналы в одну строку через пробел:");
    List<Long> denominations = inputDenominations();

    long count = allWays(sum, denominations);

    logger.log(Level.INFO, "Всего комбинаций размена {0}", count);
  }

  /**
   * Получение суммы от пользователя.
   */
  public static long inputSum() {
    Scanner in = new Scanner(System.in);
    long sum;
    try {
      sum = in.nextLong();
      in.nextLine();
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Неправильно введена сумма");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Не введена сумма");
    }
    if (sum <= 0) {
      throw new ArithmeticException("Введена нулевая или отрицательная сумма");
    }
    return sum;
  }

  /**
   * Получение номиналов от пользователя. Возвращает отсортированный массив номиналов.
   */
  public static List<Long> inputDenominations() {
    Scanner in = new Scanner(System.in);
    String stringOfDenominations;
    try {
      stringOfDenominations = in.nextLine();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Ошибка ввода");
    }

    /*Проверка, что в строке есть хоть что-то*/
    if (Objects.equals(stringOfDenominations, "")) {
      throw new NullPointerException("Ничего не было введено");
    }

    /*Получение отсортированного в обратном порядке списка номиналов*/
    List<Long> listOfDenominations;
    try {
      listOfDenominations = Arrays.stream(stringOfDenominations.trim().split(" "))
          .map(Long::parseLong).sorted(Comparator.reverseOrder()).toList();
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Неправильный ввод номиналов");
    }

    /*Удаление дубликатов*/
    listOfDenominations = listOfDenominations.stream().distinct().toList();

    if (listOfDenominations.get(listOfDenominations.size() - 1) <= 0) {
      throw new NumberFormatException("Среди номиналов есть отрицательные или нулевые");
    }

    return listOfDenominations;
  }

  /**
   * Оберточная функция для функции listOfAllWays.
   */
  public static List<List<Long>> listOfAllWays(long sum, List<Long> denominations) {
    return listOfAllWays(sum, denominations, new long[denominations.size()], 0, new ArrayList<>());
  }

  /**
   * Вычисляет все комбинации размена суммы. Возвращает список всех комбинаций
   * (Используется в тестах, чтобы удостовериться что комбинации правильно вычисляются)
   *
   * @param sum                         сумма, которую нужно разменять
   * @param denominations               массив всех номиналов
   * @param distributionOfDenominations массив, хранящий количество монет каждого номинала в
   *                                    комбинации (изначально состоит из нулей)
   * @param index                       индекс, указывающий, с каким номиналом нужно работать
   *                                    (используется в рекурсии)
   * @param combinations                список всех комбинаций (наполняется в рекурсии)
   */
  private static List<List<Long>> listOfAllWays(long sum, List<Long> denominations,
      long[] distributionOfDenominations,
      int index, List<List<Long>> combinations) {
    int n = denominations.size();
    long value; // для записи всевозможных промежуточных значений

    if (index == n - 1) {
      if (sum % denominations.get(index) == 0) {
        distributionOfDenominations[index] = sum / denominations.get(index);
        List<Long> combination = constructionOfCombination(denominations,
            distributionOfDenominations);
        combinations.add(combination);
      }
    } else {
      long l = sum / denominations.get(index);
      for (long i = l; i >= 0; --i) {
        value = sum - i * denominations.get(index);
        distributionOfDenominations[index] = i;
        listOfAllWays(value, denominations, distributionOfDenominations, index + 1, combinations);
      }
    }
    return combinations;
  }

  /**
   * Оберточная функция для функции allWays.
   */
  public static long allWays(long sum, List<Long> denominations) {
    return allWays(sum, denominations, new long[denominations.size()], 0);
  }

  /**
   * Вычисляет все комбинации размена суммы и выводит их. Возвращает число всех комбинаций
   *
   * @param sum                         сумма, которую нужно разменять
   * @param denominations               массив всех номиналов
   * @param distributionOfDenominations массив, хранящий количество монет каждого номинала в
   *                                    комбинации (изначально состоит из нулей)
   * @param index                       индекс, указывающий, с каким номиналом нужно работать
   *                                    (используется в рекурсии)
   */
  private static long allWays(long sum, List<Long> denominations,
      long[] distributionOfDenominations,
      int index) {
    int n = denominations.size();
    long value; // для записи всевозможных промежуточных значений
    long count = 0; // cчетчик комбинаций

    if (index == n - 1) {
      if (sum % denominations.get(index) == 0) {
        count = 1;
        distributionOfDenominations[index] = sum / denominations.get(index);
        printCombination(denominations, distributionOfDenominations);
      }
    } else {
      long l = sum / denominations.get(index);
      for (long i = l; i >= 0; --i) {
        value = sum - i * denominations.get(index);
        distributionOfDenominations[index] = i;
        count += allWays(value, denominations, distributionOfDenominations, index + 1);
      }
    }
    return count;
  }

  /**
   * Вывод комбинации.
   */
  public static void printCombination(List<Long> denominations,
      long[] distributionOfDenominations) {
    int n = denominations.size();
    logger.info("Begin of combination");
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < distributionOfDenominations[i]; ++j) {
        logger.log(Level.INFO, "{0}", denominations.get(i));
      }
    }
    logger.info("End of combination");
  }

  /**
   * Создает кобинацию в виде списка на основе массива новиналов и массива, содержащим числа монет
   * каждого номинала.
   */
  public static List<Long> constructionOfCombination(List<Long> denominations,
      long[] distributionOfDenominations) {
    int n = denominations.size();
    List<Long> combination = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < distributionOfDenominations[i]; ++j) {
        combination.add(denominations.get(i));
      }
    }
    return combination;
  }
}
