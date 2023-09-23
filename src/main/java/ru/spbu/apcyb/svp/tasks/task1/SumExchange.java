package ru.spbu.apcyb.svp.tasks.task1;

import java.util.List;
import java.util.Objects;

/**
 * This class is needed exclusively for the implementation of the key method.
 * <br>
 * The search for exchange combinations.
 *
 * @author Evgeny
 */

public class SumExchange {

  private final int sum;
  private final List<Integer> denominations;

  public SumExchange(int sum, List<Integer> denominations) {
    this.sum = sum;
    this.denominations = denominations;
  }

  /**
   * The method of finding all possible exchange options.
   *
   * @return All possible exchange options
   */

  public Combinations allCombinationsOfExchanges() {

    Combinations[][] combinations = new Combinations[denominations.size()][sum + 1];
    combinations[0][0] = new Combinations();
    combinations[0][0].add(new Combination());

    for (int i = 0; i < sum; i++) {
      for (int j = 0; j < denominations.size(); j++) {
        for (int k = j; k < denominations.size(); k++) {

          if (i + denominations.get(k) <= sum) {

            if (combinations[k][i + denominations.get(k)] == null) {
              combinations[k][i + denominations.get(k)] = new Combinations();
            }
            combinations[k][i + denominations.get(k)].add(combinations[j][i]);
          }
        }
        addNewCoinAtArrayOfCombinations(i, j, combinations);
        combinations[j][i] = null;
      }
    }
    return addCombinationsInAnswer(combinations);
  }

  /**
   * The method takes only complete lists of combinations from the array of all combinations.
   * <br>
   * And groups them together.
   *
   * @param combinations array of all combinations
   * @return List of complete combinations of all possible numbers that make up the sum
   */
  private Combinations addCombinationsInAnswer(Combinations[][] combinations) {
    Combinations answer = new Combinations();
    for (int i = 0; i < denominations.size(); i++) {
      answer.add(combinations[i][sum]);
    }
    return answer;
  }

  /**
   * The method adds a new coin from the denominations to our combinations, if possible. Forming new
   * combinations.
   *
   * @param i            column with numbers from 0 to the sum
   * @param j            lines with nominal numbers
   * @param combinations array of all combinations
   */

  private void addNewCoinAtArrayOfCombinations(int i, int j, Combinations[][] combinations) {
    if (i + denominations.get(j) <= sum) {
      combinations[j][i + denominations.get(j)].add(denominations.get(j));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SumExchange that = (SumExchange) o;
    return sum == that.sum && Objects.equals(denominations, that.denominations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sum, denominations);
  }
}
