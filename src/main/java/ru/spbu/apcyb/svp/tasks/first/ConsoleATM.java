package ru.spbu.apcyb.svp.tasks.first;

import java.util.List;
import java.util.Scanner;

public class ConsoleATM {
    /**
     * Use ATM through CLI
     */
    @SuppressWarnings({"java:S106", "java:S135"})
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество номиналов: ");

        int n = in.nextInt();
        int[] denominators = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Введите №" + (i + 1) + " номинал: ");
            denominators[i] = in.nextInt();
        }

        AutomatedTellerMachine atm = new AutomatedTellerMachine(denominators);

        while (true) {
            System.out.print("Введите сумму для размена (-1 для выхода): ");
            int s = in.nextInt();
            if (s == -1) {
                break;
            }

            List<List<Integer>> combinations = atm.getCombinations(s);
            if (combinations.isEmpty()) {
                System.out.println("Невозможно представить в виде суммы номиналов.");
                continue;
            }

            System.out.println("Возможные размены (" + combinations.size() + "):");
            for (List<Integer> combination : combinations) {
                System.out.println(combination);
            }
        }
    }
}
