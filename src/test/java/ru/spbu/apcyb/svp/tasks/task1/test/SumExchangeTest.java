package ru.spbu.apcyb.svp.tasks.task1.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task1.Combination;
import ru.spbu.apcyb.svp.tasks.task1.Combinations;
import ru.spbu.apcyb.svp.tasks.task1.SumExchange;

/**
 * Tests for the SumExchangeClass class.
 *
 * @author Evgeny
 * @see SumExchange
 */

class SumExchangeTest {

  @Test
  void allCombinationsOfExchangesNormal1() {
    Combination combination = new Combination();
    combination.add(2);
    combination.add(3);
    Combinations expectedCombinations = new Combinations();
    expectedCombinations.add(combination);

    List<Integer> denomination = Arrays.asList(2, 3);
    SumExchange actualSumExchange = new SumExchange(5, denomination);
    Combinations actualCombinations = actualSumExchange.allCombinationsOfExchanges();

    assertEquals(expectedCombinations.toString(), actualCombinations.toString());
  }

  @Test
  void allCombinationsOfExchangesNormal2() {
    List<Integer> denomination = Arrays.asList(1, 2);
    SumExchange actualSumExchange = new SumExchange(4, denomination);
    Combinations actualCombinations = actualSumExchange.allCombinationsOfExchanges();

    assertEquals("[1 1 1 1 , 1 1 2 , 2 2 ]", actualCombinations.toString());
  }

  @Test
  void allCombinationsOfExchangesTestSize() {
    List<Integer> denomination = Arrays.asList(1, 2, 3);
    SumExchange actualSumExchange = new SumExchange(10, denomination);
    int actualSize = actualSumExchange.allCombinationsOfExchanges().getListOfCombinations().size();

    assertEquals(14, actualSize);
  }

  @Test
  void allCombinationsOfExchangesTestEmpty() {
    List<Integer> denomination = Arrays.asList(5, 6);
    SumExchange actualSumExchange = new SumExchange(8, denomination);
    Combinations actualCombinations = actualSumExchange.allCombinationsOfExchanges();

    assertEquals("[]", actualCombinations.toString());
  }

}
