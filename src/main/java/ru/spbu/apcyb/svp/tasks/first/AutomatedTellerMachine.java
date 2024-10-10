package ru.spbu.apcyb.svp.tasks.first;

import java.util.ArrayList;
import java.util.List;

/** Simple ATM. */
public class AutomatedTellerMachine {
  private final int[] denominations;

  public AutomatedTellerMachine(int[] denominations) {
    this.denominations = denominations;
  }

  /** Function for get all possible splits for the amount by available denominations. */
  public List<List<Integer>> getCombinations(int amount) {
    List<List<Integer>> result = new ArrayList<>();

    findCombinations(amount, result);

    return result;
  }

  private void findCombinations(int amount, List<List<Integer>> result) {
    findCombinations(amount, result, new ArrayList<>(), 0);
  }

  private void findCombinations(
      int amount, List<List<Integer>> result, List<Integer> combination, int start) {
    if (amount < 0) {
      return;
    }

    if (amount == 0) {
      result.add(new ArrayList<>(combination)); // Copy combination
      return;
    }

    for (int i = start; i < denominations.length; i++) {
      combination.add(denominations[i]);
      findCombinations(amount - denominations[i], result, combination, i);
      combination.remove(combination.size() - 1);
    }
  }
}
