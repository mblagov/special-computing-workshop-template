
package ru.spbu.apcyb.svp.tasks;

import java.util.*;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Задание 1.
 */
public class Task1 {
    private static final Logger logger = Logger.getLogger(Task1.class.getName());

    /**
     * Проверка корректного ввода числа
     * @return введённое число
     */


    public static int correctInput() {
        Scanner in = new Scanner(System.in);
        int input;
        try {
            input = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка ввода!");
        }
        return input;
    }

    /**
     * Проверка число на положительность.
     *
     * @param number число для проверки.
     * @return новое число.
     */

    public static int positivityValidation(int number) {
        if (number <= 0) {
            logger.info("Ошибка; Введите значение > 0: ");
            number = correctInput();
        }
        return number;
    }

    /**
     * Вводим необходимую сумму .
     */

    public static int enterSum() {
        logger.info("Введите сумму для размена:\n");
        int sum = correctInput();
        return positivityValidation(sum);
    }

    /**
     * Ввод количества монет.
     */

    public static int enterAmount() {
        logger.info("Введите количество купюр n = ");
        int n = correctInput();
        return positivityValidation(n);
    }

    /**
     * Ввод номиналов монет.
     *
     * @param n Количество монет.
     * @return массив c монетами.
     */



    public static int[] inputOfCoins(int n, int sum) {
        int[] coins = new int[n];
        logger.info("Введите купюры:\n");
        for (int i = 0; i < n; i++) {
            coins[i] = correctInput();
            coins[i] = positivityValidation(coins[i]);
            coins[i] = checkingTooBigElements(coins[i], sum);
        }
        Arrays.sort(coins);
        return Arrays.stream(coins)
                .distinct()
                .toArray();

    }

    /**
     * Проверка величины монет относительно суммы.
     *
     * @param coin номинал для проверки .
     * @param sum сумма, с которой сравниваем.
     * @return обработанный массив.
     */

    public static int checkingTooBigElements(int coin, int sum) {
        if (coin > sum) {
            String str = "Ошибка; Введите номинал < " + sum ;
            logger.info(str);
            coin = correctInput();
        }
        return coin;
    }

    /**
     * Вывод комбинаций
     *
     * @param coins        массив номиналов
     * @param counter      массив использований каждого номинала
     * @param num          номер размена
     */

    public static void output(int[] coins, int[] counter, int num) {
        StringBuilder bld = new StringBuilder();
        bld.append("Комбинация №").append(num).append(" ");
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] != 0) {
                bld.append(String.valueOf(coins[i]).repeat(Math.max(0, counter[i])));
            }
        }
        String str = bld.toString();
        logger.info(str);
    }


    /**
     * Поиск комбинаций.
     *
     * @param sum          Меняемая сумма
     * @param tempSum      Сумма после вычета номинала
     * @param coins        Массив номиналов
     * @param counter      Счётчик использования каждого номинала
     * @param index        Номинал, с которым работаем
     * @param n          Счётчик размена
     * @return Номер размена.
     */

    public static int searchForCombinations(int sum, int tempSum, int[] coins, int[] counter, int index, int n) {
        int div = sum / coins[index];
        for (int i = 0; i <= div; i++) {
            if (tempSum >= 0) {
                counter[index] = i;
                if (tempSum == 0) {
                    n++;
                    output(coins, counter, n);
                } else if (index + 1 < coins.length) {
                    n = searchForCombinations(sum, tempSum, coins, counter, index + 1, n);
                }
            }
            tempSum -= coins[index];
        }
        return n;
    }

    /**
     *
     *
     * @param args .
     */

    public static void main(String[] args) {
        int sum = enterSum();
        int amountOfCoins = enterAmount();
        int[] coins = inputOfCoins(amountOfCoins, sum);
        int[] counter = new int[coins.length];
        searchForCombinations(sum, sum, coins, counter, 0, 0);
    }
}
