package ru.spbu.apcyb.svp.tasks;

import java.util.*;


/**
 * Задание 1.
 */
public class Task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //List<String> banknotes = new ArrayList<>();

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


        System.out.print("----------------------\n");

        int maxBanknote = -1;
        for (int num : banknotes) { //а как иначе найти максимум?
            if (maxBanknote < num) {
                maxBanknote = num;
            }
        }
        List<List<Integer>> result = exchangeOfBanknotes(amount, maxBanknote, banknotes);
        System.out.print("number of combinations: ");
        System.out.println(result.size());
        if (result.size() != 0) {
            System.out.print("combinations:\n");
            for (var combination : result) {
                for (int banknote : combination) {
                    System.out.print(banknote);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
        } else {
            System.out.print("Sorry, not today");
        }
        in.close();

    }

    public static List<List<Integer>> exchangeOfBanknotes(int amount, int minvalue, int[] banknotes) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        return exchangeOfBanknotes(amount, minvalue, banknotes, valueList, result);
    }

    private static List<List<Integer>> exchangeOfBanknotes(int amount, int minvalue, int[] banknotes, List<Integer> valueList, List<List<Integer>> result) {
        if (amount == 0) {
            result.add(valueList);
            return result;
        }
        int b = 0;
        int i;
        for (i = 0; i < banknotes.length; i++) {
            b = banknotes[i];
            if ((minvalue >= b) && (amount >= b)) {
                List<Integer> copyValueList = new ArrayList<>(valueList);
                copyValueList.add(b);
                exchangeOfBanknotes(amount - b, b, banknotes, copyValueList, result);

            }
        }
        if ((amount >= b) && i == 0) {
            List<Integer> copyValueList = new ArrayList<>(valueList);
            copyValueList.add(b);
            exchangeOfBanknotes(amount - b, b, banknotes, copyValueList, result);

        }
        return result;
    }
}