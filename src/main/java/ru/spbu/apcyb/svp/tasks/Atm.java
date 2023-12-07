package ru.spbu.apcyb.svp.tasks;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class provides solution for the ATM problem.
 */
public class Atm {
    long sum;
    List<Long> denominations;


    public Atm() {
    }

    public void getInput() {
        System.out.println("Please, enter the following parameters:");

        System.out.print("Amount value for exchange: ");
        this.sum = getInputSum();

        System.out.print("Banknote denomination values: ");
        this.denominations = getInputDen();

        System.out.println("-----------------------------------------");
        System.out.println("Entered data");
        System.out.println("Amount value for exchange:    " + sum);
        System.out.print("Banknote denomination values: ");
        for (Long den: denominations) {
            System.out.print(den + " ");
        }
        System.out.println();
    }

    /**
     * Reads input of amount value for exchange.
     *
     * @return amount value for exchange
     */
    public long getInputSum() {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        long sumValue;
        while (string.isEmpty()) {
            System.out.println("Input cannot be empty, please enter again: ");
            string = input.nextLine();
        }
        try {
            sumValue = Long.parseLong(string);
            if (sumValue < 0) {
                System.out.println("Amount value must be positive, "
                        + "please enter again: ");
                sumValue = getInputSum();
            }
            if (sumValue == 0) {
                System.out.println("Amount value must not be zero, "
                        + "please enter again: ");
                sumValue = getInputSum();
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException("Error! "
                    + "Incorrect data in amount input.");
        }
        return sumValue;
    }

    /**
     * Reads input of banknote denomination values.
     * @return array of banknote denomination values
     */
    public List<Long> getInputDen() {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        while (string.isEmpty()) {
            System.out.println("Input cannot be empty, please enter again: ");
            string = input.nextLine();
        }
        String[] strArray = string.split(" ");
        List<Long> denominationsValues = new ArrayList<>();
        try {
            for (String s : strArray) {
                long den = Long.parseLong(s);
                if (den <= 0) {
                    throw new RuntimeException("Error! Values must be positive");
                }
                if (!denominationsValues.contains(den)) {
                    denominationsValues.add(den);
                }
            }


        } catch (NumberFormatException e) {
            throw new RuntimeException("Error! "
                    + "Incorrect data in denominations input.");
        }
        return sort(denominationsValues);
    }

    /**
     * Sorts the array of denominations descending order.
     * @param denominationsArray array of denominations
     * @return sorted array of banknote denomination values
     */
    public List<Long> sort(List<Long> denominationsArray) {
        denominationsArray.sort(Collections.reverseOrder());
        return denominationsArray;
    }

    /**
     * Finds coin exchange combinations.
     * @param sumValue amount value for exchange
     * @param index start value of index is 0
     * @return list of combinations
     */
    public List<long[]> findCombinations(long sumValue, int index) {
        if (sumValue == 0) {
            throw new IllegalArgumentException("Error! sumValue cannot be zero.");
        } else if (denominations == null) {
            throw new IllegalArgumentException("Error! denominations should contain at list 1 value");
        }
        List<long[]> combinations = new ArrayList<>();
        while (sumValue > 0 && index < denominations.size()) {
            long curDen = denominations.get(index);

            if (curDen == 0) {
                throw new IllegalArgumentException("Error! denomination value cannot be zero.");
            } else if (curDen < 0) {
                throw new IllegalArgumentException("Error! denomination value must be positive");
            }

            if (sumValue == curDen) {
                long[] newCombination = new long[denominations.size()];
                newCombination[index] += 1;
                if (!combinations.contains(newCombination)) {
                    combinations.add(newCombination);
                }
            } else {
                List<long[]> tempComb = findCombinations(sumValue - denominations.get(index), index);
                for (long[] combination : tempComb) {
                    combination[index] += 1;
                    if (combinations.contains(combination)) {
                        tempComb.remove(combination);
                    }
                }
                combinations.addAll(tempComb);
            }
            index++;
        }
        return combinations;
    }

    public int getNumberOfCombinations(List<long[]> combinations) {
        return combinations.size();
    }

    /**
     * Prints number of combinations and list of combinations.
     * @param combinations list of combinations
     */
    public void printCombinations(List<long[]> combinations) {
        System.out.println("-----------------------------------------");
        System.out.println("Number of combinations: " + getNumberOfCombinations(combinations));
        System.out.println("Combinations: ");
        for (long[] combination : combinations) {
            for (int i = 0; i < combination.length; i++) {
                System.out.println("denomination: " + denominations.get(i) + ", count: " + combination[i]);
            }
            System.out.println();
        }

    }

    /**
     * Start point.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Task 1. ATM");

        boolean t = true;
        while (t) {
            Atm changeMachine = new Atm();

            changeMachine.getInput();
            changeMachine.printCombinations(changeMachine.findCombinations(changeMachine.sum, 0));

            System.out.println("Continue work? 0 - no, any number - yes");
            if (input.nextInt() == 0) {
                t = false;
            }
        }

    }
}
