package ru.spbu.apcyb.svp.tasks.task1;

import java.util.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class bankomat {
    private static final Logger logger = Logger.getLogger(bankomat.class.getName());
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        logger.info("Введите сумму монет: ");
        String stringAmount = scanner.nextLine();
        logger.info("Введите номиналы монет: ");
        String stringCoins = scanner.nextLine();
        scanner.close();

        List<List<Long>> answer = coinChange(stringAmount, stringCoins);
        logger.info("Число комбинаций: ");
        System.out.println(answer.size());
        logger.info("Комбинации: ");
        answer.forEach(System.out::println);
    }

    public static long[] inputLongList(String stringCoins) {

        String[] splitString = stringCoins.split("\\D+");

        long[] coins = new long[splitString.length];

        for (int i = 0; i < coins.length; i++) {
            coins[i] = Long.parseLong(splitString[i]);
            if (coins[i] <= 0) {
                throw new NumberFormatException("Номиналы введены некорректно");
            }
        }
        if (coins.length == 0) {
            throw new NumberFormatException("Номиналы введены некорректно");
        }
        return coins;
    }

    public static List<List<Long>> coinChange(String stringAmount, String stringCoins) {

        long amount = Long.parseLong(stringAmount);
        if (amount <= 0) {
            throw new NumberFormatException("Сумма введена некорректно");
        }

        long [] coins = inputLongList(stringCoins);
        coins = Arrays.stream(coins).distinct().toArray();
        List<Long> denominations = new ArrayList<>();
        for (long coin : coins) {
            denominations.add(coin);
        }
        Collections.sort(denominations);

        List<List<Long>> resultcomb = new ArrayList<>();
        List<Long> currcomb = new ArrayList<>();
        long maxdenomination = denominations.get(denominations.size()-1);

        coinChangeHelper(amount, maxdenomination, denominations, currcomb, resultcomb);
        return resultcomb;
    }

    private static void coinChangeHelper(long amount, long maxcoin, List<Long> coins, List<Long> currentcomb, List<List<Long>> result) {
        if (amount == 0) {
            result.add(new ArrayList<>(currentcomb));
            return;
        }
        if (amount < 0) {
            return;
        }

        long currentcoin;
        for (int i = 0; i < coins.size(); i++) {
            currentcoin = coins.get(i);
            if ((maxcoin >= currentcoin) && (amount >= currentcoin)) {
                currentcomb.add(currentcoin);
                coinChangeHelper(amount - currentcoin, currentcoin, coins, currentcomb, result);
                currentcomb.remove(currentcomb.size() - 1);
            }
        }
    }

}