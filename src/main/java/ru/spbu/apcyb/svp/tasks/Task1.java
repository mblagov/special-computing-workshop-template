package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Задание 1.
 */
public class Task1 {

  public static List<Long> parseInput(String line) throws NumberFormatException, ArithmeticException {
    Set<Long> values = new TreeSet<>();
    long[] input;
    try {
       input = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong)
          .toArray();
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Provided value is not a number");
    }

    for (int i = 0; i < input.length; i++) {
      if (input[i] <= 0) {
        throw new ArithmeticException("Provided value is not positive");
      }
      if (i != 0) {
        values.add(input[i]);
      }
    }

    if (values.isEmpty()) {
      throw new NumberFormatException("Not enough values");
    }

    ArrayList<Long> res = new ArrayList<>(values);
    res.add(0, input[0]);

    return res;
  }

  public static void fillCombinationsList(
      List<ArrayList<Long>> combinations,
      List<Long> values,
      Long currentSum,
      int valueIndex,
      List<Long> combination
  ) {
    if (currentSum == 0) {
      combinations.add((ArrayList<Long>) combination);
    } else if (valueIndex < values.size()) {
      for (long i = 0; i * values.get(valueIndex) <= currentSum; i++) {
        ArrayList<Long> nextCombination = new ArrayList<>(combination);
        nextCombination.add(i);
        fillCombinationsList(
            combinations,
            values,
            currentSum - i * values.get(valueIndex),
            valueIndex + 1,
            nextCombination
        );
      }
    }
  }

  public static String prepareOutput(
      List<ArrayList<Long>> combinations,
      List<Long> values
  ) {
    StringBuilder output = new StringBuilder();
    output.append(combinations.size());
    if (!combinations.isEmpty()) {
      output.append("\n");
    }
    for (ArrayList<Long> combination : combinations) {
      for (int i = 0; i < combination.size(); i++) {
        for (long j = 0; j < combination.get(i); j++) {
          output.append(values.get(i));
          if (i != combination.size() - 1 || j != combination.get(i) - 1) {
            output.append(" ");
          }
        }
      }
      if (combination != combinations.get(combinations.size() - 1)) {
        output.append("\n");
      }
    }

    return output.toString();
  }

  public static String getAllCombinations(String line) {
    List<Long> input = parseInput(line);

    Long sum = input.get(0);
    ArrayList<Long> values = (ArrayList<Long>) input.stream()
        .skip(1)
        .limit(input.size())
        .collect(Collectors.toList());

    values.sort(Collections.reverseOrder());
    ArrayList<ArrayList<Long>> combinations = new ArrayList<>();

    fillCombinationsList(
        combinations,
        values,
        sum,
        0,
        new ArrayList<>()
    );

    if (combinations.size() == 1 && combinations.get(0).isEmpty()) {
      return "0";
    }

    Collections.reverse(combinations);

    return prepareOutput(combinations, values);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();

    String output = getAllCombinations(line);

    Logger logger = Logger.getLogger(Task1.class.getName());

    logger.log(Level.INFO, output);
  }
}
