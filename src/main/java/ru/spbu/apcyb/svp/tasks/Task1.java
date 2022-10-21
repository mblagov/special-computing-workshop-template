package ru.spbu.apcyb.svp.tasks;

import java.util.*;


/**
 * Задание 1.
 */
public class Task1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("input the amount: ");
        String stringAmount = in.nextLine();

        System.out.print("input banknotes: ");
        String stringBanknotes = in.nextLine();

        in.close();

        exchangeOfBanknotes(stringAmount, stringBanknotes);
    }

    public static long[] inputLongList(String stringBanknotes){

        String[] splitStr = stringBanknotes.split("\\D+");


        long[] banknotes = new long[splitStr.length];

        for (int i = 0; i < banknotes.length; i++) {
            try {
                banknotes[i] = Long.parseLong(splitStr[i]);
                if (banknotes[i] <= 0){throw new NumberFormatException();}
                }catch(NumberFormatException e) {
                System.out.println("incorrect input banknotes");
                throw e;}
        }
        if (banknotes.length == 0){
            System.out.println("incorrect input banknotes");
            throw new NumberFormatException();}
        return banknotes;
    }

    public static void printExchangeOfBanknotes(List<List<Long>> result, long amount, long[] banknotes){
        System.out.println("----------------------");

        System.out.printf("amount: %d\n", amount);
        System.out.print("banknotes: ");

        for (long banknote : banknotes){
            System.out.printf("%d ", banknote);
        }
        System.out.println();
        System.out.printf("number of combinations:  %d\n", result.size());
        if (result.size() != 0) {
            System.out.print("combinations:\n");
            for (List<Long> combination : result) {
                for (Long banknote : combination) {
                    System.out.printf("%d ", banknote);
                }
                System.out.println();
            }
        } else { System.out.println("Sorry, not today"); }
    }

    public static List<List<Long>> exchangeOfBanknotes(String stringAmount, String stringBanknotes){
        List<List<Long>> result = new ArrayList<>();
        List<Long> valueList = new ArrayList<>();
        long amount;
        try {
        amount = Long.parseLong(stringAmount); } catch(NumberFormatException e){
            System.out.println("incorrect input amount");
            throw e;
        }
        if (amount<=0){
            System.out.println("incorrect input amount");
            throw new NumberFormatException();
        }
        long[] banknotes = inputLongList(stringBanknotes);
        banknotes = Arrays.stream(banknotes).distinct().toArray(); //дубликаты убирает
        long maxBanknote = -1;
        for (long num : banknotes) { //а как иначе найти максимум?
            if (maxBanknote < num) {
                maxBanknote = num;
            }
        }

        exchangeOfBanknotes(amount, maxBanknote, banknotes, valueList, result);
        printExchangeOfBanknotes(result, amount, banknotes);

        return result;
    }

    private static void exchangeOfBanknotes(long amount, long minvalue, long[] banknotes, List<Long> valueList, List<List<Long>> result) {
        if (amount == 0) {
            result.add(valueList);
            return;
        }
        long b;
        int i;
        for (i = 0; i < banknotes.length; i++) {
            b = banknotes[i];
            if ((minvalue >= b) && (amount >= b)) {
                List<Long> copyValueList = new ArrayList<>(valueList);
                copyValueList.add(b);
                exchangeOfBanknotes(amount - b, b, banknotes, copyValueList, result);

            }
        }
    }
}
