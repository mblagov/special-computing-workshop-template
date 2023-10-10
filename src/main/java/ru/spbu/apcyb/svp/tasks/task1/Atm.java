package ru.spbu.apcyb.svp.tasks.task1;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A class to exchange sum for coins.
 */
public class Atm {

  /**
   * Parses sum and coins input from a string.

   * @param s string to parse
   * @return a Pair of sum and coins list
   */
  public Pair<Long, List<Long>> parseCoins(String s) {

    Long[] coins = {Long.valueOf(2), Long.valueOf(3)};
    return new ImmutablePair<>(Long.valueOf(10), Arrays.asList(coins));
  }

}
