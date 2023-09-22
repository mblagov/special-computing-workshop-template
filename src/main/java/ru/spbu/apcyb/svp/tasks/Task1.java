package ru.spbu.apcyb.svp.tasks;

import java.util.*;


/**
 * Задание 1.
 */
public class Task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("input the amount: ");
        int amount = in.nextInt();

        System.out.print("input banknotes: ");
        in.nextLine();
        String list1 = in.nextLine();

        String[] splitStr = list1.split("\\D+");
        int[] banknotes = new int[splitStr.length];
        for (int i = 0; i < banknotes.length; i++) {
            banknotes[i] = Integer.parseInt(splitStr[i]);
        }
        in.close();

        exchangeOfBanknotes(amount, banknotes);
    }

    public static void printExchangeOfBanknotes(List<List<Integer>> result, int amount, int[] banknotes){
        System.out.println("----------------------");

        System.out.printf("amount: %d\n", amount);
        System.out.print("banknotes: ");

        for (var banknote : banknotes){
            System.out.printf("%d ", banknote);
        }
        System.out.println();
        System.out.printf("number of combinations:  %d\n", result.size());
        if (result.size() != 0) {
            System.out.print("combinations:\n");
            for (var combination : result) {
                for (int banknote : combination) {
                    System.out.printf("%d ", banknote);
                }
                System.out.println();
            }
        } else { System.out.println("Sorry, not today"); }
    }

    public static List<List<Integer>> exchangeOfBanknotes(int amount, int[] banknotes) {
        banknotes = Arrays.stream(banknotes).distinct().toArray(); //дубликаты убирает
        int maxBanknote = -1;
        for (int num : banknotes) { //а как иначе найти максимум?
            if (maxBanknote < num) {
                maxBanknote = num;
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        exchangeOfBanknotes(amount, maxBanknote, banknotes, valueList, result);
        printExchangeOfBanknotes(result, amount, banknotes);
        return result;
    }

    private static void exchangeOfBanknotes(int amount, int minvalue, int[] banknotes, List<Integer> valueList, List<List<Integer>> result) {
        if (amount == 0) {
            result.add(valueList);
            return;
        }
        int b,i;
        for (i = 0; i < banknotes.length; i++) {
            b = banknotes[i];
            if ((minvalue >= b) && (amount >= b)) {
                List<Integer> copyValueList = new ArrayList<>(valueList);
                copyValueList.add(b);
                exchangeOfBanknotes(amount - b, b, banknotes, copyValueList, result);

            }
        }}}

//        if ((amount >= b) && i == 0) {
//            List<Integer> copyValueList = new ArrayList<>(valueList);
//            copyValueList.add(b);
//            exchangeOfBanknotes(amount - b, b, banknotes, copyValueList, result);
//        }