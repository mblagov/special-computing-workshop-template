package ru.spbu.apcyb.svp.tasks.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is created to store a single digit combination.
 *
 * @author Evgeny
 */

public class Combination {

  private final List<Integer> coins;

  public Combination() {
    this.coins = new ArrayList<>();
  }

  /**
   * Constructor - creating a new object to store the combination.
   *
   * @param combination combination of numbers
   */

  public Combination(Combination combination) {
    coins = new ArrayList<>(combination.coins);
  }

  public void add(int coin) {
    coins.add(coin);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    coins.forEach(el -> stringBuilder.append(el).append(" "));
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Combination that = (Combination) o;
    return Objects.equals(coins, that.coins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coins);
  }
}
