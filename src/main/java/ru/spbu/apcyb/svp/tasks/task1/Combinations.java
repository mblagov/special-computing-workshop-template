package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for storing multiple combinations.
 *
 * @author Evgeny
 * @see Combination
 */

public class Combinations {

  private final List<Combination> listOfCombinations;

  public Combinations() {
    this.listOfCombinations = new ArrayList<>();
  }

  /**
   * Adding one coin to combinations.
   *
   * @param coin one coin from denomination
   */

  public void add(int coin) {
    for (Combination combination : listOfCombinations) {
      combination.add(coin);
    }
  }

  public void add(Combination combination) {
    listOfCombinations.add(combination);
  }

  /**
   * Adding new combinations to a list.
   *
   * @param combinations multiple combinations
   * @see Combinations
   */

  public void add(Combinations combinations) {
    if (combinations == null) {
      return;
    }
    for (Combination combination : combinations.listOfCombinations) {
      this.listOfCombinations.add(new Combination(combination));
    }
  }

  public List<Combination> getListOfCombinations() {
    return listOfCombinations;
  }

  @Override
  public String toString() {
    return
        listOfCombinations + "";
  }
}

