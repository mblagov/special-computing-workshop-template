package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Задание 1.
 */
public class Task1 {

    private static final Logger logger = LogManager.getLogger(Task1.class);

    /**
     * Запуск приложения.
     */
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var amount = scanner.nextLong();

        var notesString = scanner.nextLine().trim();
        if (notesString.trim().isEmpty()) {
            notesString = scanner.nextLine().trim();
        }

        var notes = Arrays.stream(notesString.split(" "))
            .map(Long::parseLong)
            .toList();

        printCombinations(amount, notes);
    }

    public static void printCombinations(
        long amount,
        final List<Long> notes
    ) {
        Task1.findCombinations(amount, notes, combination -> logger.log(Level.INFO, combination));
    }

    /**
     * Считает всевозможные комбинации, как с помощью купюр из notes выдать amount денег.
     *
     * @param amount - Сколько нужно выдать денег
     * @param notes  - Список купюр
     */
    public static List<ArrayList<Long>> getCombinations(long amount, final List<Long> notes) {
        var combinations = new ArrayList<ArrayList<NotesPack>>();
        Task1.findCombinations(amount, notes, combinations::add);

        return combinations.stream().map(combination -> {
            var values = new ArrayList<Long>();
            combination.forEach(pack -> {
                for (int i = 0; i < pack.count; i++) {
                    values.add(pack.value);
                }
            });
            return values;
        }).toList();
    }

    private static void findCombinations(
        long amount,
        final List<Long> notes,
        final Consumer<ArrayList<NotesPack>> onCombination
    ) {
        if (notes.isEmpty()) {
            throw new IllegalArgumentException("At least one note value must be provided");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var sortedNotes = new ArrayList<>(notes);
        sortedNotes.sort(Comparator.reverseOrder());

        findCombinations(amount, sortedNotes, onCombination, 0, new ArrayList<>());
    }

    private static void findCombinations(
        long amount,
        final List<Long> sortedNotes,
        final Consumer<ArrayList<NotesPack>> onCombination,
        int currentIndex,
        ArrayList<NotesPack> currentSequence
    ) {
        if (amount == 0) {
            onCombination.accept(new ArrayList<>(currentSequence));
            return;
        }

        for (int i = currentIndex, s = sortedNotes.size(); i < s; i++) {
            long note = sortedNotes.get(i);
            long count = amount / note;
            if (count == 0) {
                continue;
            }

            var pack = new NotesPack(note, count);
            do {
                currentSequence.add(pack);
                findCombinations(amount - note * pack.count, sortedNotes, onCombination, i + 1,
                    currentSequence);
                currentSequence.remove(currentSequence.size() - 1);
                pack = new NotesPack(pack.value, pack.count - 1);
            } while (pack.count > 0);
        }
    }

    private record NotesPack(long value, long count) {

        public String toString() {
            return value + " (x" + count + ")";
        }
    }
}
