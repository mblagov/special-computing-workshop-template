package ru.spbu.apcyb.svp.tasks.task1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task1.Combination;

/**
 * Tests for the Combination class.
 *
 * @author Evgeny
 * @see Combination
 */
class CombinationTest {

  @Test
  void addNormal() {
    int coin = 5;
    Combination combination = new Combination();
    combination.add(coin);

    String actualCombinationString = combination.toString();
    String expectedCombinationString = coin + " ";

    assertEquals(expectedCombinationString, actualCombinationString);
  }

  @Test
  void toStringNormal() {
    Combination combination = new Combination();
    for (int i = 0; i < 5; i++) {
      combination.add(i);
    }
    String expectedString = "0 1 2 3 4 ";
    String actualString = combination.toString();
    assertEquals(expectedString, actualString);
  }

  @Test
  void equalsNormal() {
    Combination expectedCombination = new Combination();
    for (int i = 0; i < 5; i++) {
      expectedCombination.add(i);
    }

    Combination actualCombination = new Combination();
    for (int i = 0; i < 4; i++) {
      actualCombination.add(i);
    }
    assertNotEquals(expectedCombination, actualCombination);
  }

  @Test
  void constructorTest() {
    Combination combination = new Combination();
    combination.add(5);
    combination.add(4);

    Combination actualsCombination = new Combination(combination);
    assertEquals("5 4 ", actualsCombination.toString());
  }


}
