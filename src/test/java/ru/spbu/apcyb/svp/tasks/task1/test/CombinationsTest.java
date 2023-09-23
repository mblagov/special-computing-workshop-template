package ru.spbu.apcyb.svp.tasks.task1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task1.Combination;
import ru.spbu.apcyb.svp.tasks.task1.Combinations;

/**
 * Tests for the Combinations class.
 *
 * @author Evgeny
 * @see Combinations
 */

class CombinationsTest {

  private Combinations actualCombinations;

  @BeforeEach
  void setUp() {
    actualCombinations = new Combinations();
    for (int i = 0; i < 4; i++) {
      Combination combination = new Combination();
      combination.add(i);
      actualCombinations.add(combination);
    }
  }

  @Test
  void addCoinNormal() {
    int coin = 3;
    actualCombinations.add(coin);
    assertEquals("[0 3 , 1 3 , 2 3 , 3 3 ]", actualCombinations.toString());
  }

  @Test
  void addCombinationsNormal() {
    Combinations combinations = new Combinations();
    for (int i = 4; i < 7; i++) {
      Combination combination = new Combination();
      combination.add(i);
      combinations.add(combination);
    }

    actualCombinations.add(combinations);

    assertEquals("[0 , 1 , 2 , 3 , 4 , 5 , 6 ]", actualCombinations.toString());
  }

  @Test
  void getListOfCombinationsNormal() {
    List<Combination> combinationList = actualCombinations.getListOfCombinations();
    assertEquals(combinationList.toString(), actualCombinations.toString());
  }
}
