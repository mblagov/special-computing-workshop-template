package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Задание 1.
 */
public class Task1 {

  public static void recursion(
      List<ArrayList<Integer>> combinations,
      List<Integer> values,
      int currentSum,
      int valueIndex,
      List<Integer> combination
  ) {
    if (currentSum == 0) {
      combinations.add((ArrayList<Integer>) combination);
    } else if (valueIndex < values.size()) {
      for (int i = 0; i * values.get(valueIndex) <= currentSum; i++) {
        ArrayList<Integer> nextCombination = new ArrayList<>(combination);
        for (int j = 0; j < i; j++) {
          nextCombination.add(values.get(valueIndex));
        }
        recursion(
            combinations,
            values,
            currentSum - i * values.get(valueIndex),
            valueIndex + 1,
            nextCombination
        );
      }
    }
  }

  public static List<ArrayList<Integer>> getAllCombinations(int[] input) {
    int sum = input[0];
    ArrayList<Integer> values = new ArrayList<>();
    for (int i = 1; i < input.length; i++) {
      values.add(input[i]);
    }

    Collections.sort(values);
    ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();

    recursion(
        combinations,
        values,
        sum,
        0,
        new ArrayList<>()
    );

    if (combinations.size() == 1 && combinations.get(0).isEmpty()) {
      return new ArrayList<>();
    }

    return combinations;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
        .toArray();

    List<ArrayList<Integer>> combinations = getAllCombinations(input);

    Logger logger = Logger.getLogger(Task1.class.getName());

    logger.log(Level.INFO, () -> String.valueOf(combinations.size()));
    for (ArrayList<Integer> combination : combinations) {
      logger.log(Level.INFO, () -> combination.toString().replaceAll("[^\\p{N}\\s]+", ""));
    }
  }
}
