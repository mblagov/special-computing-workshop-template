package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Задание 1.
 */
public class Task1 {
  /*
   * finds all ways to exchange passed value with passed coins
   * params: total -- value to exchange; int
   *         coinsStr -- exchange values; list of strings
   * returns: solutions in next format "coin:number of coins"; list of hashmaps
   */
  static List<HashMap<Integer, Integer>> findSolutions(int total, List<Integer> coins) {
    List<HashMap<Integer, Integer>> resList = new ArrayList<>();
    HashMap<Integer, Integer> start = new HashMap<>();
    coins.forEach(coin -> start.put(coin, 0));

    recursionSolver(total, coins, 0, start, resList);

    return resList;
  }

  private static  HashMap<Integer, Integer> recursionSolver(int total, List<Integer> coins,
      int curVal, HashMap<Integer, Integer> solution, List<HashMap<Integer, Integer>> resList) {
    if (total < 0) {
      return new HashMap<>();
    } else if (total == 0) {
      return solution;
    } else {
      for (Integer coin : coins) {
        if (coin >= curVal) {
          HashMap<Integer, Integer> newSolution = new HashMap<>(solution);
          newSolution.put(coin, newSolution.get(coin) + 1);
          HashMap<Integer, Integer> unfilteredSolution = recursionSolver(total - coin, coins,
              coin, newSolution, resList);
          if (!unfilteredSolution.isEmpty()) {
            resList.add(unfilteredSolution);
          }
        }
      }
    }
    return new HashMap<>();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int total;
    try {
       total = scanner.nextInt();
    } catch (Exception incorrectTotalCoinInput) {
      throw new IllegalArgumentException("can't parse first line as int");
    }

    scanner.nextLine();

    List<String> coinsStr;
    List<Integer> coins;
    try {
      coinsStr = List.of(scanner.nextLine().split("\\s+"));
      coins = new ArrayList<>();
      coinsStr.forEach(coin -> coins.add(Integer.parseInt(coin)));
    } catch (Exception incorrectListOfCoinsInput) {
      throw new IllegalArgumentException("can't parse first line as list of integers");
    }
    scanner.close();

    List<HashMap<Integer, Integer>> resList = findSolutions(total, coins);

    System.out.println(resList.size());
    for (HashMap<Integer, Integer> res : resList) {
      for (var entry : res.entrySet()) {
        System.err.print(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.err.println();
    }
  }
}

