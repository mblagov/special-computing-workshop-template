package ru.spbu.apcyb.svp.tasks.first;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleATM {
    /**
     * Use ATM through CLI
     */
    @SuppressWarnings({"java:S106", "java:S135"})
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = 0;
        while (n < 1) {
            n = askInt("Введите количество номиналов: ", in, out, i -> i > 0);
        }
        int[] denominators = new int[n];

        for (int i = 0; i < n; i++) {
            denominators[i] = askInt("Введите №" + (i + 1) + " номинал: ", in, out);
        }

        AutomatedTellerMachine atm = new AutomatedTellerMachine(denominators);

        while (true) {
            int s = askInt("Введите сумму для размена (-1 для выхода): ", in, out, i -> i > 0 || i == -1);
            if (s == -1) {
                break;
            }

            List<List<Integer>> combinations = atm.getCombinations(s);
            if (combinations.isEmpty()) {
                out.println("Невозможно представить в виде суммы номиналов.");
                out.flush();
                continue;
            }

            out.println("Возможные размены (" + combinations.size() + "):");
            for (List<Integer> combination : combinations) {
                out.println(combination);
            }
            out.flush();
        }
    }


    /**
     * Shortcut for {@code askInt(String question, Scanner in, PrintWriter out, null)}
     */
    private static int askInt(String question, Scanner in, PrintWriter out) {
        return askInt(question, in, out, null);
    }

    /**
     * Prompts the user for an integer with an optional predicate check.
     *
     * @param question question for user to ask int value
     * @param in scanner for user input
     * @param out print writer for questions and warnings
     * @param predicate check condition if int input provided. If it's null this check skips.
     * @return int value from user.
     */
    private static int askInt(String question, Scanner in, PrintWriter out, Predicate<Integer> predicate) {
        while (true) {
            out.print(question);
            out.flush();

            if (!in.hasNextInt()) {
                out.println("Invalid input.");
                in.next();
                continue;
            }

            int res = in.nextInt();
            if (predicate != null && !predicate.test(res)) {
                out.println("Input does not match condition.");
                continue;
            }

            return res;
        }
    }
}
